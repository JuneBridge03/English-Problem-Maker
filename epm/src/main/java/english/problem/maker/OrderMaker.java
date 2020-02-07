package english.problem.maker;

/**
 * Github: https://github.com/JuneBridge03/English-Problem-Maker
 * 
 * The MIT License
 * Copyright (c) 2020 Jun Gyo, Kim(JuneBridge03, https://github.com/JuneBridge03)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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

        final Section s = hwpFile.getBodyText().getSectionList().get(0);

        final ArrayList<String> texts = new ArrayList<String>();

        for (int i = 1; i < s.getParagraphCount(); i += 2) {
            try {
                final String text = s.getParagraph(i).getText().getNormalString(0);
                texts.add(text);
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

        HWPWriter.toFile(hwpFile,
                "done" + File.separator + arg.split("-")[0] + "-순서배열-" + Integer.toString(numbers) + ".hwp");
    }
}