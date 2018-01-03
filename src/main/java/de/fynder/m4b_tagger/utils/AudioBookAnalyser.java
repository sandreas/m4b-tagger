package de.fynder.m4b_tagger.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import javax.imageio.ImageIO;
import java.io.*;

import java.util.AbstractMap;
import java.util.concurrent.TimeUnit;


public class AudioBookAnalyser {


    public AudioBook analyse(File f) throws IOException, InterruptedException {
        AudioBook book = new AudioBook();

        String ffmpeg = "ffmpeg";

        ProcessBuilder pb = new ProcessBuilder(ffmpeg, "-i", f.getAbsolutePath(), "-f", "ffmetadata", "-");
        //pb.redirectErrorStream(true);
        Process p = pb.start();

        p.waitFor(5, TimeUnit.SECONDS);

        parseMetaDataStream(book, p.getInputStream());

        File tempDir = new File("tmp/cover-extract");

        File tempFile = new File(tempDir.getAbsolutePath() + "/" + f.getName() + "-cover.jpg");
//        tempFile = new File("/tmp/test.jpg");
//File tempFile = File.createTempFile("MyAppName-", ".tmp");
//        tempFile.deleteOnExit();
        ProcessBuilder pbImage = new ProcessBuilder(ffmpeg, "-i", f.getAbsolutePath(), "-c:v", "copy", tempFile.getAbsolutePath(), "-nostdin");
        //pb.redirectErrorStream(true);
        Process pImage = pbImage.start();
        pImage.waitFor(5, TimeUnit.SECONDS);
        // Thread.sleep(5000);
        try {
//            BufferedImage image = ImageIO.read(fis); //reading the image file
            book.cover = ImageIO.read(tempFile); // eventually C:\\ImageTest\\pic2.jpg
        } catch (IOException e) {
            book.cover = null;
        } finally {
            tempFile.delete();
        }
        // Cover
        // ffmpeg -i modified.m4b -c:v copy cover.jpg
        // Path tempFile = Files.createTempFile("tempfiles", ".tmp");

//        if (book.cover == null) {
//            System.out.println("no cover found");
//        } else {
//            System.out.println("cover extracted");
//        }

        return book;
    }


    private void parseMetaDataStream(AudioBook book, InputStream inputStream) throws IOException {
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputReader);
        Chapter chap = parseMetaDataTillChapter(book, reader);
        parseChapters(book, chap, reader);

    }

    private Chapter parseMetaDataTillChapter(AudioBook book, BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(";")) {
                continue;
            }

            if (line.trim().toLowerCase().equals("[chapter]")) {
                return new Chapter();
            }

            handleLine(book, line);
            // System.out.println(line);
        }
        return null;
    }

    private void parseChapters(AudioBook book, Chapter chap, BufferedReader reader) throws IOException {
        String line;
        double timeBase = 1;
        while (chap != null && (line = reader.readLine()) != null) {
            if (line.trim().toLowerCase().equals("[chapter]")) {
                book.chapters.add(chap);
                chap = new Chapter();
                continue;
            }

            AbstractMap.SimpleEntry splitted = split(line, "=");
            if (splitted == null) {
                continue;
            }

            if (splitted.getKey().toString().toLowerCase().equals("timebase")) {
                String[] tb = splitted.getValue().toString().split("/");
                if (tb.length == 0) {
                    continue;
                }
                int base = 1;
                if (tb.length > 0) {
                    base = Integer.parseInt(tb[0]);
                }

                int sub = 1000;
                if (tb.length > 1) {
                    sub = Integer.parseInt(tb[1]);
                }
                timeBase = (double) base / ((double) sub) * 1000;
            }

            if (splitted.getKey().toString().toLowerCase().equals("start")) {
                chap.startMilliSeconds = Double.parseDouble(splitted.getValue().toString()) * timeBase;
                continue;
            }

            if (splitted.getKey().toString().toLowerCase().equals("end")) {
                chap.durationMilliSeconds = (Double.parseDouble(splitted.getValue().toString()) * timeBase) - chap.startMilliSeconds;
                continue;
            }

            if (splitted.getKey().toString().toLowerCase().equals("title")) {
                chap.title = splitted.getValue().toString();
            }

        }
    }

    private void handleLine(AudioBook book, String line) {

        AbstractMap.SimpleEntry keyValuePair = split(line, "=");
        if (keyValuePair == null) {
            return;
        }
        String key = (String) keyValuePair.getKey();
        String value = (String) keyValuePair.getValue();

        // Name\=Name => Name\\\=Name
        if (key.equals("title")) {
            book.name = value;
            return;
        }


        if (key.equals("sort_composer")) {
            book.sortComposer = value;
            return;
        }

        if (key.equals("album")) {
            book.album = value;
            return;
        }


        if (key.equals("artist")) {
            book.artist = value;
            return;
        }

        if (key.equals("album_artist")) {
            book.albumArtist = value;
            return;
        }


        if (key.equals("composer")) {
            book.composer = value;
            return;
        }

        if (key.equals("grouping")) {
            book.grouping = value;
            return;
        }

        if (key.equals("genre")) {
            book.genre = value;
            return;
        }

        if (key.equals("comment")) {
            book.comments = value;
            return;
        }

        if (key.equals("lyrics")) {
            book.lyrics = value;
            return;
        }

        if (key.equals("sort_name")) {
            book.sortName = value;
            return;
        }

        if (key.equals("sort_album")) {
            book.sortAlbum = value;
            return;
        }

        if (key.equals("sort_artist")) {
            book.sortArtist = value;
            return;
        }

        if (key.equals("sort_album_artist")) {
            book.sortAlbumArtist = value;
            return;
        }

        if (key.equals("encoder")) {
            book.encodingTool = value;
            return;
        }

        if (key.equals("description")) {
            book.description = value;
            return;
        }

        if (key.equals("compilation") && value.equals("1")) {
            book.compilation = true;
            return;
        }

        if (key.equals("track")) {
            String[] parts = value.split("/");
            if (parts.length > 0) {
                book.track.index = Integer.parseInt(parts[0]);
            }
            if (parts.length > 1) {
                book.track.total = Integer.parseInt(parts[1]);
            }
            return;
        }

        if (key.equals("disc")) {
            String[] parts = value.split("/");
            if (parts.length > 0) {
                book.disk.index = Integer.parseInt(parts[0]);
            }
            if (parts.length > 1) {
                book.disk.total = Integer.parseInt(parts[1]);
            }
            return;
        }

        if (key.equals("date")) {
            book.releaseDate = parseDateTime(value);
            return;
        }


        System.out.println(key + "   ==   " + value);
        /*
        Missing:
        major_brand   ==   mp42
        minor_version   ==   0
        compatible_brands   ==   mp42isom
         */

    }

    private AbstractMap.SimpleEntry split(String line, String splitBy) {
        int equalSignIndex = line.indexOf(splitBy);
        if (equalSignIndex < 0) {
            return null;
        }
        String value = line.substring(equalSignIndex + 1);
        StringBuffer unescapedValue = new StringBuffer();

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == '\\') {
                unescapedValue.append(value.charAt(i + 1));
                i++;
                continue;
            }

            unescapedValue.append(value.charAt(i));
        }
        return new AbstractMap.SimpleEntry(line.substring(0, equalSignIndex).trim(), unescapedValue.toString());
    }


    private static DateTime parseDateTime(String input) {
        String[] supportedFormats = new String[]{
                "YYYY-MM-dd",
                "YYYY",
        };

        for (String pattern : supportedFormats) {
            try {
                return DateTime.parse(input, DateTimeFormat.forPattern(pattern));
            } catch (Exception e) {

            }
        }


        return null;
    }

    private static void extractCover(AudioBook book) {

    }

}
