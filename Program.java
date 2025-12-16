/*
#################################################
#  #
#################################################
*/

/*
################################################################################
# Program.java doit contenir la logique de fonctionnement de la machine à café #
################################################################################
*/
import java.util.Scanner;
public class Program {
    public static void main(String[] args) {
        
        /*
        ########################################
        # Vérification de l'état de la machine #
        ########################################
        */
       // Si le compteur de cafés servis atteint 5 : la machine se déclare Hors Service.
       Stocks Stocks = new Stocks();
       while (true) { 
           if (Stocks.GobeletsServis >= 6) {
               System.out.println("ERREUR : Machine entartrée - Appelez le technicien");
               break;
            } else {
                boolean AfficherMenuTechnicien = true;
                boolean AfficherMenuClient = true;
                Caisse Caisse = new Caisse();
                Scanner scanner = new Scanner(System.in);
                while (AfficherMenuClient) {
                    System.out.println("------------------------------------------------");
                    System.out.println("ETAT : EAU:" + Stocks.Eau + " | CAFE:" + Stocks.Cafe + " | GOBELETS:" + (Stocks.GobeletsMax - Stocks.GobeletsServis));
                    System.out.println("CREDIT : " + Caisse.ArgentTransaction + "€");
                    System.out.println("------------------------------------------------");
                    System.out.println("1. insérer une pièce 0.50€ (simuler)");
                    System.out.println("2. choisir café court (1.50€)");
                    System.out.println("3. choisir café allongé (2.00€)");
                    System.out.println("4. Menu technicien (maintenance)");
                    System.out.print("Entrez votre choix: ");
                String ChoixClient = scanner.nextLine().trim();

                
                if ("1".equals(ChoixClient)) { // Ajouter 0.50€ à ArgentTransaction
                    System.out.println("------------------------------------");
                    System.out.println("Pièce de 0.50€ insérée.");
                    System.out.println("------------------------------------");
                    Caisse.ArgentTransaction += 0.50;
                    
                    
                } else if ("2".equals(ChoixClient) || ("3".equals(ChoixClient))) { // café allonge ou court choisi
                    
                    // vérifie le crédit
                    if (("2".equals(ChoixClient) && Caisse.ArgentTransaction < 1.50) || ("3".equals(ChoixClient) && Caisse.ArgentTransaction < 2.00)) {
                        System.out.println("------------------------------------");
                        System.out.println("Crédit insuffisant, ajoutez de la monnaie");
                        System.out.println("------------------------------------");
                        continue;
                    }
                    
                    // vérifie les stocks et affiche l'erreur appropriée à partir le ligne 78
                    if (Stocks.Eau > 10.0) {
                        if (Stocks.Cafe > 5.0) {
                            if (Stocks.GobeletsMax - Stocks.GobeletsServis > 0) {
                                // sert le café
                                if ("2".equals(ChoixClient)) {
                                    // café court
                                    CafeCourt CafeCourt = new CafeCourt();
                                    Stocks.Eau -= CafeCourt.EauCafeCourt;
                                    Stocks.Cafe -= CafeCourt.CafeCafeCourt;
                                    Stocks.GobeletsServis += 1;
                                    Caisse.ArgentTransaction -= CafeCourt.PrixCafeCourt;
                                    Caisse.ArgentTotal += CafeCourt.PrixCafeCourt;
                                    System.out.println("------------------------------------");
                                    System.out.println("Votre café court est prêt !");
                                    System.out.println("------------------------------------");
                                    
                                } else {
                                    // café allongé
                                    CafeAllonge CafeAllonge = new CafeAllonge();
                                    Stocks.Eau -= CafeAllonge.EauCafeAllonge;
                                    Stocks.Cafe -= CafeAllonge.CafeCafeAllonge;
                                    Stocks.GobeletsServis += 1;
                                    Caisse.ArgentTransaction -= CafeAllonge.PrixCafeAllonge;
                                    Caisse.ArgentTotal += CafeAllonge.PrixCafeAllonge;
                                    System.out.println("------------------------------------");
                                    System.out.println("Votre café allongé est prêt !");
                                    System.out.println("------------------------------------");
                                }
                            } else {
                                System.out.println("----------------------------------------------------------------");
                                System.out.println("Stock de gobelets insuffisant. Veuillez contacter le technicien.");
                                System.out.println("----------------------------------------------------------------");
                            }
                        } else {
                            System.out.println("----------------------------------------------------------------");
                            System.out.println("Stock de café insuffisant. Veuillez contacter le technicien.");
                            System.out.println("----------------------------------------------------------------");
                        }
                    } else {
                        System.out.println("----------------------------------------------------------------");
                        System.out.println("Stock d'eau insuffisant. Veuillez contacter le technicien.");
                        System.out.println("----------------------------------------------------------------");
                    }
                } else if ("4".equals(ChoixClient)) { // Menu technicien
                    AfficherMenuClient = false;
                    AfficherMenuTechnicien = true;
                    while (AfficherMenuTechnicien) {
                        System.out.println("###############################");
                        System.out.println("------Menu technicien---------");
                        System.out.println("###############################");
                        System.out.println("1. Remplir l'eau" + " EAU:" + Stocks.Eau);
                        System.out.println("2. Remplir le café" + " CAFE:" + Stocks.Cafe);
                        System.out.println("3. Vider la caisse" + " CAISSE:" + Caisse.ArgentTotal + "€");
                        System.out.println("4. Détartrage (remettre à zéro le compteur de gobelets servis)");
                        System.out.println("5. Retour au menu principal"); // retour au menu principal pour éviter les culs de sac
                        System.out.print("Entrez votre choix: ");
                    String choixTechnicien = scanner.nextLine().trim();

                    if ("1".equals(choixTechnicien)) {
                        Stocks.Eau = Stocks.EauMax;
                        System.out.println("------------------------------------");
                        System.out.println("Réservoir d'eau rempli.");
                        System.out.println("------------------------------------");

                    } else if ("2".equals(choixTechnicien)) {
                        Stocks.Cafe = Stocks.CafeMax;
                        System.out.println("------------------------------------");
                        System.out.println("Réservoir de café rempli.");
                        System.out.println("------------------------------------");

                    } else if ("3".equals(choixTechnicien)) {
                        System.out.println("------------------------------------");
                        System.out.println("Caisse vidée. Montant retiré: " + Caisse.ArgentTotal + "€");
                        System.out.println("------------------------------------");
                        Caisse.ArgentTotal = 0.0;
                    } else if ("4".equals(choixTechnicien)) {
                        Stocks.GobeletsServis = 0;
                        System.out.println("------------------------------------");
                        System.out.println("Machine détartrée. Compteur de gobelets remis à zéro.");
                        System.out.println("------------------------------------");
                        
                    } else if ("5".equals(choixTechnicien)) {
                        System.out.println("------------------------------------");
                        System.out.println("Retour au menu principal.");
                        System.out.println("------------------------------------");
                        AfficherMenuTechnicien = false;
                        AfficherMenuClient = true;
                    }
                }
            } else {
                System.out.println("------------------------------------");
                System.out.println("Choix invalide. Veuillez réessayer.");
                System.out.println("------------------------------------");
            }
        }
        scanner.close();
    }
}
}
}