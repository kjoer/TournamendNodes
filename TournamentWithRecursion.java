public class TournamentWithRecursion {
    // aufgabe a)
    public record TournamendNode(TournamendNode left, TournamendNode right, String winner, int points) {

    }

    // aufgabe b), ich gehe davon aus, dass der Gewinner hierfür übergeben wird.
    static boolean finished(final TournamendNode root) {
        if (root == null) {
            return false;
        }
        return root.winner() != null;
    }

    // aufgabe c)
    static TournamendNode setPoints(final TournamendNode node, final int points) {
        if (node == null) {
            throw new IllegalArgumentException("Der übergebene Knoten darf nicht(!) null sein!");
        }
        return new TournamendNode(node.left, node.right, node.winner, points);
    }

    //aufgabe d)
    static int powerOf2(final int nonNegativeNumber) {
        if (nonNegativeNumber == 0) {
            return 1;
        } else if (nonNegativeNumber < 0) {
            throw new IllegalArgumentException("Negative Werte nicht erlaubt");

        }
        return 2 * powerOf2(nonNegativeNumber - 1);
    }

    //alternativ hätte ich es aber so gelöst:
//aufgabe d) alternative
    static int powerOf2MyVersion(final int nonNegativeNumber) {
        int result = 1;
        if (nonNegativeNumber < 0) {
            throw new IllegalArgumentException("Darf nicht negativ sein");
        }
        for (int i = 0; i < nonNegativeNumber; i++) {
            result *= 2;

        }
        return result;
    }

    //e)
    static int rowOffset(final int level, final int height) {
        if (height >= level) {
            return powerOf2(height) / powerOf2(level);
        } else throw new IllegalArgumentException("height / level ergibt eine Kommazahl");
    }

    // f)
    static int getHeight(final TournamendNode node) {
        if (node == null) {
            return -1;
        }
        int heightRight = getHeight(node.right);
        int heightLeft = getHeight(node.left);
        return 1+Math.max(heightLeft, heightRight);
    }

    //g)
    static int countNames(final TournamendNode node) {
        if (node == null) {
            return 0;
        }
        int count = (node.winner != null) ? 1 : 0;
        count += countNames(node.left);
        count += countNames(node.right);

        return count;

    }

    //h)
    static int getNumberofLeaves(final TournamendNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int leftLeaves = getNumberofLeaves(node.left);
        int rightLeaves = getNumberofLeaves(node.right);

        return rightLeaves + leftLeaves;
    }

    //i)
    static TournamendNode addParticipant(final String name, final TournamendNode node) {
        if (node == null) {
            return new TournamendNode(null, null, name, 0);
        }
        if(node.winner != null){
            return new TournamendNode(node,new TournamendNode(null,null,name,0),name,0);
        }
        //wieder durch rekursion
            if(countNames(node.right)>countNames(node.left)) {
            //baum ganz nach links runterlaufen
            //neuen namen links einfuegen
            return new TournamendNode(addParticipant(name, node.left), node.right, node.winner, node.points);

        } else { // participant nach unten ziehen und neuen daneben einfuegen und oben neuen zwischenknoten
            //neuen namen rechts einfuegen
            //baum ganz nach rechts runterlaufen
            return new TournamendNode(node.left, addParticipant(name, node.right), node.winner, node.points);
        }

    }


    //zum Testen
    public static void main(String[] args) {

        TournamendNode testNodeRight = new TournamendNode(null, null, "yes", 4);
        TournamendNode testNodeLeft = new TournamendNode(null, null, "no", 3);
        TournamendNode testNode = new TournamendNode(testNodeRight, testNodeLeft, "K", 4);
        System.out.println(getNumberofLeaves(testNode));
        System.out.println(getHeight(testNode));
        System.out.println(getHeight(testNodeRight));

    }
}

