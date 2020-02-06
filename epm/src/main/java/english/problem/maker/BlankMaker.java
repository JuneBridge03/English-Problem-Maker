package english.problem.maker;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.bodytext.Section;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.writer.HWPWriter;

import java.io.File;
import java.util.Random;

class BlankMaker {
    public static void startFile(String arg, Integer percent) throws Exception {
        HWPFile hwpFile = HWPReader.fromFile("resources" + File.separator + arg);
        Random rand = new Random();
        if (hwpFile != null) {
            Section s = hwpFile.getBodyText().getSectionList().get(0);

            Boolean is_eng = true;

            for (int i = 1; i < s.getParagraphCount(); i++) {
                try {
                    String text = s.getParagraph(i).getText().getNormalString(0);

                    if (is_eng) {
                        String[] texts = text.split(" ");
                        for (int ii = 0; ii < texts.length; ii++) {
                            if (rand.nextInt(100) <= percent) {
                                texts[ii] = "_".repeat(texts[ii].length());
                            }
                        }
                        Util.changeParagraphText(s.getParagraph(i), String.join(" ", texts));
                    }
                    is_eng = !is_eng;
                } catch (Throwable e) {
                    break;
                }
            }
        }
        HWPWriter.toFile(hwpFile,
                "done" + File.separator + arg.split("-")[0] + "-빈칸채우기-" + Integer.toString(percent) + ".hwp");
    }
}