# waveform in javafx
https://stackoverflow.com/questions/40806836/how-to-create-an-audio-wave-in-javafx
https://github.com/pakoito/javafxmobile-plugin-ensemble/blob/master/src/main/java/ensemble/samples/charts/area/audio/AudioAreaChartApp.java
http://docs.oracle.com/javafx/2/charts/line-chart.htm#CIHGBCFI

# JavaFx Projects
https://github.com/octaviospain/Musicott/blob/master/application/src/com/transgressoft/musicott/MusicottApplication.java
http://www.oracle.com/technetwork/server-storage/ts-4842-1-159016.pdf





# Import
## Cover Art
ffmpeg -i video.mp4 -i cover.jpg -c:a copy -c:v copy -map 0 -map 1:0 video+cover.mp4

ffmpeg -i INPUT.mp4 -i IMAGE.png -acodec copy -vcodec copy -map 0 -map 1:0 OUTPUT.mp4



## Chapters / Metadata
ffprobe -show_chapters modified.m4b > modified.chapters.txt


ffmpeg -i test.m4b -i test.chapters.txt -map_metadata 1 -map_chapters 1 -f mp4 -c:s mov_chapters -vn -c:s copy -c:a:0 copy test_new.m4b
ffmpeg -i test.m4b -i test.chapters.txt -map_chapters 1 -f mp4 -vn -c:s copy -c:a:0 copy test_new.m4b


# Export

## Cover
ffmpeg -i modified.m4b -c:v copy cover.jpg







## Metadata
ffmpeg -i modified.m4b -f ffmetadata metadata.txt # redirect to metadata.txt
ffmpeg -i modified.m4b -f ffmetadata - # output to stdout

;FFMETADATA1
major_brand=mp42
minor_version=0
compatible_brands=mp42isom
sort_composer=Sortieren als Komponist
title=Name
album=Album
artist=Künstler
album_artist=Albumkünstler
composer=Komponist
grouping=Gruppierung
genre=Genre
track=1/2
disc=3/4
date=2017
comment=Kommentare
compilation=1
lyrics=Liedtext
sort_name=Sortieren als Name
sort_album=Sortieren als Album
sort_artist=Sortieren als Künstler
sort_album_artist=Sortieren als Albumkünstler
encoder=Lavf57.71.100
[CHAPTER]
TIMEBASE=1/1000
START=3
END=21296
title=Jingle und Ansage
[CHAPTER]
TIMEBASE=1/1000
START=21296
END=343376
title=Ein Junge überlebt (1)


# working import chapters
ffmpeg -i test.m4b -i test.chapters.txt -c copy -map_chapters 1 -f mp4 test_new.m4b
## format chapters


Folgende zwei Befehle bewirken das gleiche, in die MP4 werden Nero- und Quicktime-Kapitel geschrieben:
Code:
ffmpeg -i title01.mp4 -i title01.txt -c copy -map_metadata 1 title01m.mp4
ffmpeg -i title01.mp4 -i title01.txt -c copy -map_chapters 1 title01c.mp4
Der Parameter hinter -map_metadata bzw. -map_chapters bezeichnet den Index der Kapiteldatei in den Inputdateien beginnend mit "0".

Um nur Quicktime-Kapitel zu schreiben fügt man -movflags disable_chpl ein.
Code:
ffmpeg -i title01.mp4 -i title01.txt -c copy -map_metadata 1 -movflags disable_chpl title01m1.mp4
ffmpeg -i title01.mp4 -i title01.txt -c copy -map_chapters 1 -movflags disable_chpl title01c1.mp4
Die Kapiteldatei muss folgendes Format aufweisen:
Code:
;FFMETADATA1
[CHAPTER]
TIMEBASE=1/1000
START=0
#chapter ends at 0:01:00
END=60000
title=chapter \#1
Hier noch ein Beispiel:
Code:
;FFMETADATA1
[CHAPTER]
TIMEBASE=1/1000000000
START=0
END=447000000000
title=Chapter 01
[CHAPTER]
TIMEBASE=1/1000000000
START=447000000000
END=681320000000
title=Chapter 02
[CHAPTER]
TIMEBASE=1/1000000000
START=681320000000
END=905320000000
title=Chapter 03
[CHAPTER]
TIMEBASE=1/1000000000
START=905320000000
END=1125280000000
title=Chapter 04
[CHAPTER]
TIMEBASE=1/1000000000
START=1125280000000
END=1243240000000
title=Chapter 05
[CHAPTER]
TIMEBASE=1/1000000000
START=1243240000000
END=1458640000000
title=Chapter 06
[CHAPTER]
TIMEBASE=1/1000000000
START=1458640000000
END=1657360000000
title=Chapter 07
Und das sagt mediainfo dazu:
Code:
Menu #1
ID                                       : 3
Codec ID                                 : text
Duration                                 : 27mn 37s
Language                                 : English
Bit rate mode                            : CBR
00:00:00.000                             : Chapter 01
00:07:27.000                             : Chapter 02
00:11:21.320                             : Chapter 03
00:15:05.320                             : Chapter 04
00:18:45.280                             : Chapter 05
00:20:43.240                             : Chapter 06
00:24:18.640                             : Chapter 07

Menu #2
00:00:00.000                             : Chapter 01
00:07:27.000                             : Chapter 02
00:11:21.320                             : Chapter 03
00:15:05.320                             : Chapter 04
00:18:45.280                             : Chapter 05
00:20:43.240                             : Chapter 06
00:24:18.640                             : Chapter 07
Menu #1 ist Quicktime, Menu #2 Nero.


Wizard:
https://gist.github.com/jewelsea/2850787

Project settings:
https://stackoverflow.com/questions/37018567/why-my-gradle-projects-creates-separated-modules-for-main-and-test-in-intellij-i


Interesting Projects:
https://zeroturnaround.com/rebellabs/best-javafx-libraries-for-beautiful-apps-and-clean-code/
https://github.com/TestFX/TestFX

Video Tutorials:
https://www.youtube.com/watch?v=FLkOX4Eez6o


https://stackoverflow.com/questions/22569046/how-to-make-an-os-x-menubar-in-javafx
http://docs.oracle.com/javafx/2/ui_controls/menu_controls.htm
http://docs.oracle.com/javafx/2/layout/builtin_layouts.htm
http://docs.oracle.com/javafx/2/get_started/form.htm



FFmpeg ffmpeg = new FFmpeg("/path/to/ffmpeg");
FFprobe ffprobe = new FFprobe("/path/to/ffprobe");

FFmpegBuilder builder = new FFmpegBuilder()
    .setInput(in)
    .overrideOutputFiles(true)
    .addOutput("output.mp4")
        .setFormat("mp4")
        .setTargetSize(250000)

        .disableSubtitle()

        .setAudioChannels(1)
        .setAudioCodec("libfdk_aac")
        .setAudioRate(48000)
        .setAudioBitrate(32768)

        .setVideoCodec("libx264")
        .setVideoFramerate(Fraction.getFraction(24, 1))
        .setVideoResolution(640, 480)

        .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
        .done();


http://webcache.googleusercontent.com/search?q=cache:7XrzX1brU1AJ:jonhall.info/dump_and_load_metadata_with_ffmpeg/+&cd=1&hl=de&ct=clnk&gl=de&client=safari

https://github.com/klhurley/mp4parser