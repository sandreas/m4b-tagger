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
