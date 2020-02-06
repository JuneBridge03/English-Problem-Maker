package english.problem.maker;

import java.io.File;

public class App {
    public static void main(final String[] args) {
        System.out.println("[System] Start EPM System 1.0-SNAPSHOT");
        final File resources = new File("resources");
        final File[] filelist = resources.listFiles();
        try {
            for (final File file : filelist) {

                final String fname = file.getName();
                System.out.println(fname);

                if (fname.substring(fname.length() - 4).equals(".hwp")) {

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

                        default: {
                            System.out.println("Opps");
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
