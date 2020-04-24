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
import kr.dogfoot.hwplib.writer.HWPWriter;

import java.io.File;

class EmptyKoreanMaker {
    public static void startFile(String arg) throws Exception {
        HWPFile hwpFile = HWPReader.fromFile("resources" + File.separator + arg);

        Section s = hwpFile.getBodyText().getSectionList().get(0);

        for (int i = 2; i < s.getParagraphCount(); i += 2) {

            String text = s.getParagraph(i).getText().getNormalString(0);

            Util.changeParagraphText(s.getParagraph(i), "_".repeat(text.length()));

        }
        HWPWriter.toFile(hwpFile, "done" + File.separator + arg.split("-")[0] + "-해석하기" + ".hwp");
    }
}