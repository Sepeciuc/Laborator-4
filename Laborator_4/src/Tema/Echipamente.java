package Tema;
import java.io.*;
import java.util.*;
import java.util.Scanner;


abstract class Echipament implements Serializable {
    protected String denumire;
    protected int nr_inv;
    protected double pret;
    protected String zona_mag;
    protected String status;

    public Echipament(String denumire, int nr_inv, double pret, String zona_mag, String status) {
        this.denumire = denumire;
        this.nr_inv = nr_inv;
        this.pret = pret;
        this.zona_mag = zona_mag;
        this.status = status;
    }
    public String getDenumire() {
        return denumire;
    }
    public int getNrInv() {
        return nr_inv;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public String getZonaMag() {
        return zona_mag;
    }
    public double getPret() {
        return pret;
    }
    public abstract void afisare();
}

class Imprimanta extends Echipament {
    private int ppm;
    private int dpi;
    private int p_car;
    private String mod_t;

    public Imprimanta(String denumire, int nr_inv, double pret, String zona_mag, String status,
                      int ppm, int dpi, int p_car, String mod_t) {
        super(denumire, nr_inv, pret, zona_mag, status);
        this.ppm = ppm;
        this.dpi = dpi;
        this.p_car = p_car;
        this.mod_t = mod_t;
    }



    public int getPpm() {
        return ppm;
    }

    public int getDpi() {
        return dpi;
    }

    public int getP_car() {
        return p_car;
    }

    public String getModT() {
        return mod_t;
    }

    public void setModT(String mod_t) {
        this.mod_t = mod_t;
    }
    @Override
    public void afisare() {
        System.out.println("Imprimanta: " + denumire + ", Nr. inventar: " + nr_inv + ", PPM: " + ppm + ", DPI: " + dpi + ", Mod tiparire: " + mod_t);
    }
}

class Copiator extends Echipament {
    private int p_ton;
    private String format;

    public Copiator(String denumire, int nr_inv, double pret, String zona_mag, String status,
                    int p_ton, String format) {
        super(denumire, nr_inv, pret, zona_mag, status);
        this.p_ton = p_ton;
        this.format = format;
    }
    public String getFormat() {
        return format;
    }

    public int getP_ton() {
        return p_ton;
    }


    public void setFormat(String format) {
        this.format = format;
    }
    @Override
    public void afisare() {
        System.out.println("Copiator: " + denumire + ", Nr. inventar: " + nr_inv + ", Format copiere: " + format + ", Pagini/toner: " + p_ton);
    }
}

class SistemCalcul extends Echipament {
    private String tip_mon;
    private String tip_proc;
    private int c_hdd;
    private String sistem_operare;

    public SistemCalcul(String denumire, int nr_inv, double pret, String zona_mag, String status, String tip_mon, String tip_proc, int c_hdd, String sistem_operare) {
        super(denumire, nr_inv, pret, zona_mag, status);
        this.tip_mon = tip_mon;
        this.tip_proc = tip_proc;
        this.c_hdd = c_hdd;
        this.sistem_operare = sistem_operare;
    }

    public int getC_hdd() {
        return c_hdd;
    }

    public String getTip_proc() {
        return tip_proc;
    }

    public String getTip_mon() {
        return tip_mon;
    }

    public String getSistemOperare() {
        return sistem_operare;
    }


    public void setSistemOperare(String sistem_operare) {
        this.sistem_operare = sistem_operare;
    }
    @Override
    public void afisare() {
        System.out.println("Sistem de calcul: " + denumire + ", Nr. inventar: " + nr_inv + ", Monitor: " + tip_mon + ", Procesor: " + tip_proc + ", HDD: " + c_hdd + " GB, OS: " + sistem_operare);
    }
}

public class Echipamente {
    private static List<Echipament> echipamente = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {

        citireFisier();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdu numărul de inventar al imprimantei: ");
        int nrInv = scanner.nextInt();
        scanner.nextLine();  // consumăm linia rămasă după nextInt()
        System.out.print("Introdu noul mod de scriere (alb-negru / color): ");
        String nouModScriere = scanner.nextLine();


        setareModScriereImprimanta(nrInv, nouModScriere);

        System.out.print("Introdu numărul de inventar al copiatorului: ");
        int nrInvCopiator = scanner.nextInt();
        scanner.nextLine();


        System.out.print("Introdu noul format de copiere (A3 / A4): ");
        String nouFormat = scanner.nextLine();


        setareFormatCopiator(nrInvCopiator, nouFormat);
        System.out.print("Introdu numărul de inventar al sistemului de calcul: ");
        int nrInvSistem = scanner.nextInt();
        scanner.nextLine();


        System.out.print("Introdu noul sistem de operare (Windows / Linux): ");
        String nouSistemOperare = scanner.nextLine();


        instalareSistemOperare(nrInvSistem, nouSistemOperare);
        afisareEchipamenteVandute();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Afisare toate echipamentele");
            System.out.println("2. Afisare imprimante");
            System.out.println("3. Afisare copiatoare");
            System.out.println("4. Afisare sisteme de calcul");
            System.out.println("5. Modificare stare echipament");
            System.out.println("6. Salvare si iesire");
            int optiune = sc.nextInt();
            switch (optiune) {
                case 1:
                    afisareToateEchipamentele();
                    break;
                case 2:
                    afisareImprimante();
                    break;
                case 3:
                    afisareCopiatoare();
                    break;
                case 4:
                    afisareSistemeCalcul();
                    break;
                case 5:
                    modificareStareEchipament(sc);
                    break;
                case 6:
                    salvareInFisier();
                    System.exit(0);
                default:
                    System.out.println("Optiune invalida.");
            }
        }
    }

    private static void afisareToateEchipamentele() {
        for (Echipament e : echipamente) {
            e.afisare();
        }
    }

    private static void afisareImprimante() {
        for (Echipament e : echipamente) {
            if (e instanceof Imprimanta) {
                e.afisare();
            }
        }
    }

    private static void afisareCopiatoare() {
        for (Echipament e : echipamente) {
            if (e instanceof Copiator) {
                e.afisare();
            }
        }
    }

    private static void afisareSistemeCalcul() {
        for (Echipament e : echipamente) {
            if (e instanceof SistemCalcul) {
                e.afisare();
            }
        }
    }

    private static void modificareStareEchipament(Scanner sc) {
        System.out.println("Introduceti numarul de inventar: ");
        int nr_inv = sc.nextInt();
        for (Echipament e : echipamente) {
            if (e.nr_inv == nr_inv) {
                System.out.println("Introduceti noua stare (achizitionat/expus/vandut): ");
                String nouaStare = sc.next();
                e.setStatus(nouaStare);
                System.out.println("Stare modificata cu succes.");
                return;
            }
        }
        System.out.println("Echipament cu acest numar de inventar nu a fost gasit.");
    }

    public static void setareModScriereImprimanta(int nrInv, String nouModScriere) {
        boolean imprimantaGasita = false;

        for (Echipament echipament : echipamente) {
            if (echipament instanceof Imprimanta) {
                Imprimanta imprimanta = (Imprimanta) echipament;
                if (imprimanta.getNrInv() == nrInv) {

                    if (nouModScriere.equalsIgnoreCase("alb-negru") || nouModScriere.equalsIgnoreCase("color")) {
                        imprimanta.setModT(nouModScriere);
                        System.out.println("Modul de scriere pentru imprimanta " + imprimanta.getDenumire() + " a fost actualizat la: " + nouModScriere);
                    } else {
                        System.out.println("Modul de scriere este invalid. Trebuie să fie 'alb-negru' sau 'color'.");
                    }
                    imprimantaGasita = true;
                    break;
                }
            }
        }

        if (!imprimantaGasita) {
            System.out.println("Imprimanta cu numărul de inventar " + nrInv + " nu a fost găsită.");
        }
    }
    public static void setareFormatCopiator(int nrInv, String nouFormat) {
        boolean copiatorGasit = false;

        for (Echipament echipament : echipamente) {
            if (echipament instanceof Copiator) {
                Copiator copiator = (Copiator) echipament;
                if (copiator.getNrInv() == nrInv) {

                    if (nouFormat.equalsIgnoreCase("A3") || nouFormat.equalsIgnoreCase("A4")) {
                        copiator.setFormat(nouFormat);
                        System.out.println("Formatul de copiere pentru copiatorul " + copiator.getDenumire() + " a fost actualizat la: " + nouFormat);
                    } else {
                        System.out.println("Format invalid. Trebuie să fie 'A3' sau 'A4'.");
                    }
                    copiatorGasit = true;
                    break;
                }
            }
        }

        if (!copiatorGasit) {
            System.out.println("Copiatorul cu numărul de inventar " + nrInv + " nu a fost găsit.");
        }
    }
    public static void instalareSistemOperare(int nrInv, String nouSistemOperare) {
        boolean sistemGasit = false;

        for (Echipament echipament : echipamente) {
            if (echipament instanceof SistemCalcul) {
                SistemCalcul sistemCalcul = (SistemCalcul) echipament;
                if (sistemCalcul.getNrInv() == nrInv) {

                    if (nouSistemOperare.equalsIgnoreCase("Windows") || nouSistemOperare.equalsIgnoreCase("Linux")) {
                        sistemCalcul.setSistemOperare(nouSistemOperare);
                        System.out.println("Sistemul de operare pentru sistemul de calcul " + sistemCalcul.getDenumire() + " a fost actualizat la: " + nouSistemOperare);
                    } else {
                        System.out.println("Sistem de operare invalid. Trebuie să fie 'Windows' sau 'Linux'.");
                    }
                    sistemGasit = true;
                    break;
                }
            }
        }

        if (!sistemGasit) {
            System.out.println("Sistemul de calcul cu numărul de inventar " + nrInv + " nu a fost găsit.");
        }
    }
    public static void afisareEchipamenteVandute() {
        boolean existaEchipamenteVandute = false;


        for (Echipament echipament : echipamente) {

            if (echipament.getStatus().equalsIgnoreCase("vandut")) {
                existaEchipamenteVandute = true;


                System.out.println("Echipament vândut: ");
                System.out.println("Denumire: " + echipament.getDenumire());
                System.out.println("Număr inventar: " + echipament.getNrInv());
                System.out.println("Preț: " + echipament.getPret());
                System.out.println("Zona magazin: " + echipament.getZonaMag());
                System.out.println("Status: " + echipament.getStatus());


                if (echipament instanceof Imprimanta) {
                    Imprimanta imprimanta = (Imprimanta) echipament;
                    System.out.println("Tip: Imprimanta");
                    System.out.println("PPM: " + imprimanta.getPpm());
                    System.out.println("Rezolutie: " + imprimanta.getDpi());
                    System.out.println("Pagini/cartuș: " + imprimanta.getP_car());
                    System.out.println("Mod de tipărire: " + imprimanta.getModT());
                }


                if (echipament instanceof Copiator) {
                    Copiator copiator = (Copiator) echipament;
                    System.out.println("Tip: Copiator");
                    System.out.println("Pagini/toner: " + copiator.getP_ton());
                    System.out.println("Format copiere: " + copiator.getFormat());
                }


                if (echipament instanceof SistemCalcul) {
                    SistemCalcul sistem = (SistemCalcul) echipament;
                    System.out.println("Tip: Sistem de calcul");
                    System.out.println("Monitor: " + sistem.getTip_mon());
                    System.out.println("Viteza procesor: " + sistem.getTip_proc());
                    System.out.println("Capacitate HDD: " + sistem.getC_hdd());
                    System.out.println("Sistem operare: " + sistem.getSistemOperare());
                }
                System.out.println("--------------------------------------------------");
            }
        }

        if (!existaEchipamenteVandute) {
            System.out.println("Nu există echipamente vândute.");
        }
    }


    private static void citireFisier() {
        String fileName = "electronice.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String denumire = parts[0];
                int nr_inv = Integer.parseInt(parts[1]);
                double pret = Double.parseDouble(parts[2]);
                String zona_mag = parts[3];
                String status = parts[4];
                String tipEchipament = parts[5].toLowerCase();

                switch (tipEchipament) {
                    case "imprimanta":
                        int ppm = Integer.parseInt(parts[6]);
                        String[] dpiParts = parts[7].split("x");
                        int dpi = Integer.parseInt(dpiParts[0].trim());
                        int p_car = Integer.parseInt(parts[8]);
                        String mod_t = parts[9].toLowerCase();
                        Imprimanta imprimanta = new Imprimanta(denumire, nr_inv, pret, zona_mag, status, ppm, dpi, p_car, mod_t);
                        echipamente.add(imprimanta);
                        break;

                    case "copiator":
                        int p_ton = Integer.parseInt(parts[6]);
                        String format_copiere = parts[7].toUpperCase();
                        Copiator copiator = new Copiator(denumire, nr_inv, pret, zona_mag, status, p_ton, format_copiere);
                        echipamente.add(copiator);
                        break;

                    case "sistem de calcul":
                        String tip_mon = parts[6];
                        String vit_proc = parts[7];
                        int c_hdd = Integer.parseInt(parts[8]);
                        String sistem_operare = parts[9].toLowerCase();
                        SistemCalcul sistem = new SistemCalcul(denumire, nr_inv, pret, zona_mag, status, tip_mon, vit_proc, c_hdd, sistem_operare);
                        echipamente.add(sistem);
                        break;


                    default:
                        System.out.println("Tip necunoscut de echipament: " + tipEchipament);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul " + fileName + " nu a fost gasit.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Eroare la parsarea unui număr: " + e.getMessage());
        }
    }


    private static void salvareInFisier() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("echip.bin"))) {
            oos.writeObject(echipamente);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}