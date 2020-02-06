package english.problem.maker;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.bodytext.Section;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.tool.paragraphadder.ParagraphAdder;
import kr.dogfoot.hwplib.writer.HWPWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class OrderMaker {
    public static void startFile(final String arg, final Integer numbers) throws Exception {
        final HWPFile hwpFile = HWPReader.fromFile("resources" + File.separator + arg);
        final Random rand = new Random();
        if (hwpFile != null) {
            final Section s = hwpFile.getBodyText().getSectionList().get(0);

            Boolean is_eng = true;

            final ArrayList<String> texts = new ArrayList<String>();

            for (int i = 1; i < s.getParagraphCount(); i++) {
                try {
                    final String text = s.getParagraph(i).getText().getNormalString(0);
                    if (is_eng) {
                        texts.add(text);
                    }
                    is_eng = !is_eng;
                } catch (Throwable e) {
                    break;
                }
            }

            final ArrayList<List<String>> arrays = new ArrayList<>();

            for (Integer i = 0; i < numbers; i++) {
                arrays.add(texts.subList(i * texts.size() / numbers, (i + 1) * texts.size() / numbers));
            }

            final String abcd = "ABCDEFGHIJKLMNOPQRXTUVWXYZ";

            Integer paragraph_index = 1;

            Integer number = 1;

            while (arrays.size() != 0) {
                Integer i = rand.nextInt(arrays.size());
                Util.changeParagraphText(s.getParagraph(paragraph_index++),
                        "(" + abcd.substring(number - 1, number++) + ")");
                for (String string : arrays.get(i)) {
                    Util.changeParagraphText(s.getParagraph(paragraph_index++), string);
                }
                arrays.remove(arrays.get(i));
            }

            for (Integer i = 0; i < 2; i++) {
                ParagraphAdder paraAdder = new ParagraphAdder(hwpFile, s);
                paraAdder.add(hwpFile, s.getParagraph(1));
            }

            s.getParagraph(paragraph_index++).deleteText();

            Util.changeParagraphText(s.getParagraph(paragraph_index++), "___" + (" -> ___".repeat(number - 2)));

            for (Integer i = paragraph_index; i < s.getParagraphCount(); i++) {
                s.getParagraph(i).deleteText();
            }

        }
        HWPWriter.toFile(hwpFile,
                "done" + File.separator + arg.split("-")[0] + "-순서배열-" + Integer.toString(numbers) + ".hwp");
    }
}