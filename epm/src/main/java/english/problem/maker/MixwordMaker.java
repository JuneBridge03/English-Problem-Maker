package english.problem.maker;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.bodytext.Section;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.writer.HWPWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class MixwordMaker{
    public static void startFile(String arg) throws Exception {
        HWPFile hwpFile = HWPReader.fromFile("resources" + File.separator + arg);
        if (hwpFile != null) {
            Section s = hwpFile.getBodyText().getSectionList().get(0);

            Boolean is_eng = true;
            
            for(int i = 1; i < s.getParagraphCount(); i++){
                String text = s.getParagraph(i).getText().getNormalString(0);
                if (is_eng){
                    String[] texts = text.split(" ");
                    Util.changeParagraphText(s.getParagraph(i), "[ " + String.join(" / ", mixStrings(texts)) + " ]");
                }
                is_eng = !is_eng;
            }
        }
        HWPWriter.toFile(hwpFile, "done" + File.separator + arg.split("-")[0] + "-배열하기" + ".hwp");
    }
    private static String[] mixStrings(String[] texts){
        ArrayList<String> ntexts = new ArrayList<String>();
        ArrayList<String> ltexts = new ArrayList<String>(Arrays.asList(texts));
        Random rand = new Random();
        while(ltexts.size() != 0){
            if(ltexts.size() == 1){
                ntexts.add(ltexts.get(0));
                break;
            }
            Integer i = rand.nextInt(ltexts.size() - 1);
            ntexts.add(ltexts.get(i));
            ltexts.remove(ltexts.get(i));

        }
        return (String[]) ntexts.toArray(new String[ntexts.size()]);
    }
}