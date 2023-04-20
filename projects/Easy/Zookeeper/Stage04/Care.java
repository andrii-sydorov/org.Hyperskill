package projects.Easy.Zookeeper.Stage04;

import java.util.Scanner;

/**
 * Stage 4/4: Sustainable care <3
 * 
 * Description
 * 
 * Now it's time to make our project user-friendly. In this final stage, you'll make your software ready for the zoo staff to use. Your program should understand the habitat numbers, show the animals, and be able to work continuously without having to be restarted.
 * 
 * Objectives
 * 
 * Your tasks at this point:
 * Your program should repeat the behavior from the previous stage, but now in a loop.
 * Do not forget to include an exit opportunity: inputting exit should end the program.
 * When the program is done running, it should print: See you later!
 * 
 * Examples
 * The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.
 * The final version of the program should run like this:
 * 
 * Example 1
 * 
 * Please enter the number of the habitat you would like to view: > 3
 * 
 * Switching on the camera in the goose habitat...

                                    _
                                ,-"" "".
                              ,'  ____  `.
                            ,'  ,'    `.  `._
   (`.         _..--.._   ,'  ,'        \    \
  (`-.\    .-""        ""'   /          (  d _b
 (`._  `-"" ,._             (            `-(   \
 <_  `     (  <`<            \              `-._\
  <`-       (__< <           :
   (__        (_<_<          ;
    `------------------------------------------
The goose is staring intently at you... Maybe it's time to change the channel?
Please enter the number of the habitat you would like to view: > 1

Switching on the camera in the lion habitat...
                                               ,w.
                                             ,YWMMw  ,M  ,
                        _.---.._   __..---._.'MMMMMw,wMWmW,
                   _.-""        '''           YP"WMMMMMMMMMb,
                .-' __.'                   .'     MMMMW^WMMMM;
    _,        .'.-'"; `,       /`     .--""      :MMM[==MWMW^;
 ,mM^"     ,-'.'   /   ;      ;      /   ,       MMMMb_wMW"  @\
,MM:.    .'.-'   .'     ;     `\    ;     `,     MMMMMMMW `"=./`-,
WMMm__,-'.'     /      _.\      F'''-+,,   ;_,_.dMMMMMMMM[,_ / `=_}
"^MP__.-'    ,-' _.--""   `-,   ;       \  ; ;MMMMMMMMMMW^``; __|
           /   .'            ; ;         )  )`{  \ `"^W^`,   \  :
          /  .'             /  (       .'  /     Ww._     `.  `"
         /  Y,              `,  `-,=,_{   ;      MMMP`""-,  `-._.-,
        (--, )                `,_ / `) \/"")      ^"      `-, -;"\:
The lion is roaring!
Please enter the number of the habitat you would like to view: > exit
See you later!
 * @author SMD_ASY
 *
 */

public class Care {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String camel = """
                Switching on the camera in the camel habitat...
                 ___.-''''-.
                /___  @    |
                ',,,,.     |         _.'''''''._
                     '     |        /           \\
                     |     \\    _.-'             \\
                     |      '.-'                  '-.
                     |                               ',
                     |                                '',
                      ',,-,                           ':;
                           ',,| ;,,                 ,' ;;
                              ! ; !'',,,',',,,,'!  ;   ;:
                             : ;  ! !       ! ! ;  ;   :;
                             ; ;   ! !      ! !  ; ;   ;,
                            ; ;    ! !     ! !   ; ;
                            ; ;    ! !    ! !     ; ;
                           ;,,      !,!   !,!     ;,;
                           /_I      L_I   L_I     /_I
                Look at that! Our little camel is sunbathing!""";

        String lion = """
                Switching on the camera in the lion habitat...
                                                               ,w.
                                                             ,YWMMw  ,M  ,
                                        _.---.._   __..---._.'MMMMMw,wMWmW,
                                   _.-""        '''           YP"WMMMMMMMMMb,
                                .-' __.'                   .'     MMMMW^WMMMM;
                    _,        .'.-'"; `,       /`     .--""      :MMM[==MWMW^;
                 ,mM^"     ,-'.'   /   ;      ;      /   ,       MMMMb_wMW"  @\\
                ,MM:.    .'.-'   .'     ;     `\\    ;     `,     MMMMMMMW `"=./`-,
                WMMm__,-'.'     /      _.\\      F'''-+,,   ;_,_.dMMMMMMMM[,_ / `=_}
                "^MP__.-'    ,-' _.--""   `-,   ;       \\  ; ;MMMMMMMMMMW^``; __|
                           /   .'            ; ;         )  )`{  \\ `"^W^`,   \\  :
                          /  .'             /  (       .'  /     Ww._     `.  `"
                         /  Y,              `,  `-,=,_{   ;      MMMP`""-,  `-._.-,
                        (--, )                `,_ / `) \\/"")      ^"      `-, -;"\\:
                The lion is roaring!""";

        String deer = """
                Switching on the camera in the deer habitat...
                   /|       |\\
                `__\\       //__'
                   ||      ||
                 \\__`\\     |'__/
                   `_\\   //_'
                   _.,:---;,._
                   \\_:     :_/
                     |@. .@|
                     |     |
                     ,\\.-./ \\
                     ;;`-'   `---__________-----.-.
                     ;;;                         \\_\\
                     ';;;                         |
                      ;    |                      ;
                       \\   \\     \\        |      /
                        \\_, \\    /        \\     |\\
                          |';|  |,,,,,,,,/ \\    \\ \\_
                          |  |  |           \\   /   |
                          \\  \\  |           |  / \\  |
                           | || |           | |   | |
                           | || |           | |   | |
                           | || |           | |   | |
                           |_||_|           |_|   |_|
                          /_//_/           /_/   /_/
                Our 'Bambi' looks hungry. Let's go to feed it!""";

        String goose = """
                Switching on the camera in the goose habitat...

                                                    _
                                                ,-"" "".
                                              ,'  ____  `.
                                            ,'  ,'    `.  `._
                   (`.         _..--.._   ,'  ,'        \\    \\
                  (`-.\\    .-""        ""'   /          (  d _b
                 (`._  `-"" ,._             (            `-(   \\
                 <_  `     (  <`<            \\              `-._\\
                  <`-       (__< <           :
                   (__        (_<_<          ;
                    `------------------------------------------
                The goose is staring intently at you... Maybe it's time to change the channel?""";

        String bat = """
                Switching on the camera in the bat habitat...
                _________________               _________________
                 ~-.              \\  |\\___/|  /              .-~
                     ~-.           \\ / o o \\ /           .-~
                        >           \\  W  //           <
                       /             /~---~\\             \\
                      /_            |       |            _\\
                         ~-.        |       |        .-~
                            ;        \\     /        i
                           /___      /\\   /\\      ___\\
                                ~-. /  \\_/  \\ .-~
                                   V         V
                This bat looks like it's doing fine.""";

        String rabbit = """
                Switching on the camera in the rabbit habitat...
                         ,
                        /|      __
                       / |   ,-~ /
                      Y :|  //  /
                      | jj /( .^
                      >-"~"-v"
                     /       Y
                    jo  o    |
                   ( ~T~     j
                    >._-' _./
                   /   "~"  |
                  Y     _,  |
                 /| ;-"~ _  l
                / l/ ,-"~    \\
                \\//\\/      .- \\
                 Y        /    Y
                 l       I     !
                 ]\\      _\\    /"\\
                (" ~----( ~   Y.  )
                It looks like we will soon have more rabbits!""";

        String[] animals = { camel, lion, deer, goose, bat, rabbit };
        boolean exit = false;
        while (!exit) {
            System.out.print("Please enter the number of the habitat you would like to view: ");
            String s = sc.nextLine();
            try {
                int n = Integer.valueOf(s);
                System.out.println();
                System.out.println(animals[n]);
            } catch (NumberFormatException nfe) {
                System.out.println("See you later!");
                exit = true;
            }
        }
        sc.close();
    }
}