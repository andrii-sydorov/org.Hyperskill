package InnerClass;

/**
 * You have an outer class ChristmasTree and an inner class TreeTopper. Both
 * classes have the field color.
 * 
 * In TreeTopper class create method void sparkle.
 * 
 * For silver tree topper and green Christmas tree the output of sparkle will
 * be:
 * 
 * Sparkling silver tree topper looks stunning with green Christmas tree!
 * In the outer class create a method void putTreeTopper with one string
 * parameter color.
 * In this method, you should create an instance of an inner class with
 * parameter color, then call method sparkle of TreeTopper.
 * 
 * Please, don't use private access modifier for methods.
 * 
 * @author SMD_ASY
 *
 */

public class CreateHoliday {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ChristmasTree christmasTree = new ChristmasTree("green");
        christmasTree.putTreeTopper("silver");
    }

}

class ChristmasTree {

    private String color;

    public ChristmasTree(String color) {
        this.color = color;
    }

    // create method putTreeTopper()
    void putTreeTopper(String color) {
        ChristmasTree.TreeTopper topper = this.new TreeTopper(color);
        topper.sparkle();

    }

    class TreeTopper {

        private String color;

        public TreeTopper(String color) {
            this.color = color;
        }

        // create method sparkle()
        void sparkle() {
            if (color.equals("silver") && ChristmasTree.this.color.equals("green")) {
                System.out.println("Sparkling silver tree topper looks stunning with green Christmas tree!");
            }
        }
    }
}
