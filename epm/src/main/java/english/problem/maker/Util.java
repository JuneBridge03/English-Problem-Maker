package english.problem.maker;

/**
 * Source Code : https://github.com/neolord0/hwplib
 * Source Code License : Apache 2.0(https://github.com/neolord0/hwplib/blob/master/license.txt)
 * Source Code File : https://github.com/neolord0/hwplib/blob/master/src/main/java/kr/dogfoot/hwplib/sample/Copying_Paragraph_Between_HWPFile.java
 */

import kr.dogfoot.hwplib.object.bodytext.paragraph.Paragraph;
import kr.dogfoot.hwplib.object.bodytext.paragraph.text.HWPChar;
import kr.dogfoot.hwplib.object.bodytext.paragraph.text.HWPCharNormal;
import kr.dogfoot.hwplib.object.bodytext.paragraph.text.HWPCharType;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

class Util {

    public static void changeParagraphText(Paragraph paragraph, String newText) throws UnsupportedEncodingException {
        ArrayList<HWPChar> newCharList = getNewCharList(paragraph.getText().getCharList(), newText);
        changeNewCharList(paragraph, newCharList);
        removeLineSeg(paragraph);
        removeCharShapeExceptFirstOne(paragraph);
    }

    private static ArrayList<HWPChar> getNewCharList(ArrayList<HWPChar> oldList, String nText)
            throws UnsupportedEncodingException {
        ArrayList<HWPChar> newList = new ArrayList<HWPChar>();
        ArrayList<HWPChar> listForText = new ArrayList<HWPChar>();
        for (HWPChar ch : oldList) {
            if (ch.getType() == HWPCharType.Normal) {
                listForText.add(ch);
            } else {
                if (listForText.size() > 0) {
                    listForText.clear();
                    String newText = nText;

                    newList.addAll(toHWPCharList(newText));
                }
                newList.add(ch);
            }
        }

        if (listForText.size() > 0) {
            listForText.clear();
            String newText = nText;

            newList.addAll(toHWPCharList(newText));
        }
        return newList;
    }

    private static ArrayList<HWPChar> toHWPCharList(String text) {
        ArrayList<HWPChar> list = new ArrayList<HWPChar>();
        int count = text.length();
        for (int index = 0; index < count; index++) {
            HWPCharNormal chn = new HWPCharNormal();
            chn.setCode((short) text.codePointAt(index));
            list.add(chn);
        }
        return list;
    }

    private static void changeNewCharList(Paragraph paragraph, ArrayList<HWPChar> newCharList) {
        paragraph.getText().getCharList().clear();
        for (HWPChar ch : newCharList) {
            paragraph.getText().getCharList().add(ch);
        }
        paragraph.getHeader().setCharacterCount(newCharList.size());
    }

    private static void removeLineSeg(Paragraph paragraph) {
        paragraph.deleteLineSeg();
    }

    private static void removeCharShapeExceptFirstOne(Paragraph paragraph) {
        int size = paragraph.getCharShape().getPositonShapeIdPairList().size();
        if (size > 1) {
            for (int index = 0; index < size - 1; index++) {
                paragraph.getCharShape().getPositonShapeIdPairList().remove(1);
            }
            paragraph.getHeader().setCharShapeCount(1);
        }
    }
}