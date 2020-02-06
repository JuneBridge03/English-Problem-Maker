package english.problem.maker;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.bodytext.Section;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.writer.HWPWriter;

import java.io.File;

class EmptyMaker{
    public static void startFile(String arg) throws Exception {
        HWPFile hwpFile = HWPReader.fromFile("resources" + File.separator + arg);
        if (hwpFile != null) {
            Section s = hwpFile.getBodyText().getSectionList().get(0);

            Boolean is_eng = true;
            
            for(int i = 1; i < s.getParagraphCount(); i++){
                String text = s.getParagraph(i).getText().getNormalString(0);
                
                if (is_eng){
                    Util.changeParagraphText(s.getParagraph(i), "_".repeat(text.length()));
                }
                is_eng = !is_eng;
            }
        }
        HWPWriter.toFile(hwpFile, "done" + File.separator + arg.split("-")[0] + "-영작하기" + ".hwp");
    }
}