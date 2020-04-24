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

import java.io.File;

public class App {
    public static void main(final String[] args) {
        System.out.println("[System] Start EPM System 1.0.0v");

        try {
            for (final File file : (new File("resources")).listFiles()) {

                final String fname = file.getName();

                if (fname.substring(fname.length() - 4).equals(".hwp")) {

                    System.out.println("[System] Dectected! File name : " + fname);

                    String[] fnames = fname.substring(0, fname.length() - 4).split("-");

                    for (Integer i = 1; i < fnames.length; i++) {
                        System.out.println("[System] Detected! Code : " + fnames[i]);
                        switch (fnames[i]) {

                            case "빈칸채우기": {
                                BlankMaker.startFile(fname, Integer.parseInt(fnames[++i]));
                                break;
                            }

                            case "영작하기": {
                                EmptyMaker.startFile(fname);
                                break;
                            }

                            case "배열하기": {
                                MixwordMaker.startFile(fname);
                                break;
                            }

                            case "순서배열": {
                                OrderMaker.startFile(fname, Integer.parseInt(fnames[++i]));
                                break;
                            }

                            case "해석하기": {
                                EmptyKoreanMaker.startFile(fname);
                                break;
                            }

                            default: {
                                System.out.println("[System] Wrong Code!");
                            }

                        }
                    }
                }
            }
        } catch (final Exception e) {
            e.printStackTrace(System.out);
        } finally {
            System.out.println("[System] EPM System off..");
        }
    }
}
