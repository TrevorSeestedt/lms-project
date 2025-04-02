package Tank;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.concurrent.TimeUnit;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import java.util.UUID;



/*
*Need to add:
*Author can edit previously made module
*Automatically directs user to the yet to be completed portion of the course they choose
*User needs to be able to view comments on each module and reply to them
*User needs to be able to print out the information on a module for understanding to a text file
*When logged out, return to the sign in screen
 */


public class CourseUI {
    static RegisteredUser currentUser = null;
    static UserList userList = UserList.getInstance();
    static CourseList courseList = CourseList.getInstance();
    static Results userResults = new Results();
    static CourseSystemApplication app = new CourseSystemApplication();

    static Scanner input = new Scanner(System.in);

    //Log in or Create Account
    //Determines if user is author or regular user
    public static void initialScreen() {

        boolean initialValid = false;
        while (!initialValid) {
            System.out.println("\n**** Welcome to Our Application ****\n\n1. Log in\n2. Sign up\n3. Close Application\n\nPlease enter what you would like to do:");
            String initialInput = input.next();
            
            if (initialInput.equals("1")) {
                login();
            }
            else if (initialInput.equals("2")) {
                signUp();
                initialScreen();
            }
            else if(initialInput.equals("3")) {
                //Exiting program
                initialValid = true;
                System.out.println("\nThank you for using our system!");
                System.exit(0);
            }
            else {
                System.out.println("That is not a valid option!");
            }
        }
    }

    //Regular User Screen
    public static void studentScreen() {

        boolean RegularScreenValid = false;
        while(!RegularScreenValid) {
            System.out.println("\n**** Main Menu ****\n\n1. Enroll in a new course\n2. View current courses\n3. View grades\n4. View account information\n5. Print Cirtificate\n6. Log Out\n\nPlease enter what you would like to do:\n");
            String regularUserChoice = input.next();

            if(regularUserChoice.equals("1")) {
                    RegularScreenValid = true;
                    for (int i = 0; i < courseList.getCourses().size(); i++) {
                        System.out.println("Course Title "+(i+1) + ": "+ courseList.getCourses().get(i).getTitle());
                    }
                    System.out.println("Enter the title of the course you wish to enroll in: ");
                    
                    String courseTitle;
                    courseTitle = input.nextLine();
                    courseTitle = input.nextLine();
                    
                    Course tempCourse = courseList.getCourseByCourseTitle(courseTitle);
                    if(tempCourse != null) {
                        System.out.println("Successfully enrolled in new course!");
                        courseList.addCourse(tempCourse.getCourseID(), tempCourse.getTitle(), tempCourse.getDateCreated(), tempCourse.getLanguage(), tempCourse.getAuthor(), tempCourse.getDifficulty(), tempCourse.getReviews(), tempCourse.getSummary(), tempCourse.getModules(), tempCourse.getUserResults());
                        currentUser.addCourse(tempCourse);
                    }
                    else {
                        System.out.println("You did not enter the correct name of the course so you were not enrolled.");
                    }
                    studentScreen();

                    /*boolean selectCourseReg3 = false;
                    while(!selectCourseReg3) {
                        System.out.println("\n** Enroll in a new course **\n\n1. JavaScript\n2. HTML/CSS\n3. Python\n4. Java\n\nPlease choose a course or type 'back':");
                        String regUserLanguageChoice = input.next();
                        if(regUserLanguageChoice.equalsIgnoreCase("1")) {
                            selectCourseReg3 = true;
                            //JavaScript
                            //courseList.addCourse()
                            //Filler info
                        }
                        else if(regUserLanguageChoice.equalsIgnoreCase("2")) {
                            selectCourseReg3 = true;
                            //HTML/CSS
                            //Filler info
                        }
                        else if(regUserLanguageChoice.equalsIgnoreCase("3")) {
                            selectCourseReg3 = true;
                            //Python
                            //Filler info
                        }
                        else if(regUserLanguageChoice.equalsIgnoreCase("4")) {
                            selectCourseReg3 = true;
                            //Java
                            //Filler info
                        }
                        else if(regUserLanguageChoice.equalsIgnoreCase("back")) {
                            selectCourseReg3 = true;
                            break;
                        }
                        else {
                            System.out.println("That is not a valid choice!");
                        }
                        
                    }*/
            }
            else if(regularUserChoice.equals("2")) {
                RegularScreenValid = true;
                
                //Print Courses
                //Replace "courses" at some point
                System.out.println("\n*** Current Courses ***\n");
                //ArrayList<Course> courses = DataLoader.getCourses();
                if (!(currentUser.getCurrentCourses() == null)) {
                    for (int i = 1; i <= currentUser.getCurrentCourses().size(); i++) {
                        System.out.println(i + ". " + currentUser.getCurrentCourses().get(i-1).toString());
                    }
                    studentScreen();
                }
                else {
                    System.out.println("You arent enrolled in any courses");
                    studentScreen();
                }
                
            }
                
            else if(regularUserChoice.equals("3")) {
                RegularScreenValid = true;
                
                boolean viewRegGrades = false;
                while(!viewRegGrades) {
                    System.out.println("\n*** Grades ***\n\n");
                    //PrintGrades
                    for(int i=0;i < currentUser.getCurrentCourses().size(); ++i) {
                        Course tempCourse = currentUser.getCurrentCourses().get(i);
                        for(int j=0;j<tempCourse.getUserResults().size();++j) {
                            ArrayList<Double> tempGrades = tempCourse.getUserResults().get(j).getGrades();
                            for(int k=0;k<tempGrades.size();++k) {
                                System.out.println(tempGrades.get(k));
                            }
                        }
                    }
                    System.out.println("\n\nType 'Back' to go to the previous page");
                    String RegBack3 = input.next();
                    if(!(RegBack3.equalsIgnoreCase("back"))) {
                        System.out.println("That is not a valid choice");
                    }
                    else {
                        break; //Back in theory
                    }
                }
                studentScreen();
            }
            else if(regularUserChoice.equals("4")) {
                RegularScreenValid = true;
            

                boolean viewRegAccountInformation = false;
                while(!viewRegAccountInformation) {
                    
                    System.out.println("\n\n*** Account Information ***\n\n");
                    System.out.println(currentUser.toString());
                    System.out.println("\n\nType 'Back' to go to the previous page");
                    String RegBack4 = input.next();
                    if(!(RegBack4.equalsIgnoreCase("back"))) {
                        System.out.println("That is not a valid choice");
                    }
                    else {
                        break; //Back in theory
                    }
                }
                studentScreen();
            }
            else if(regularUserChoice.equals("6")) {
                RegularScreenValid = true;

                boolean logOutReg = false;
                while(!logOutReg) {
                    System.out.println("\n*** Logout ***\n\nAre you sure you want to logout? (Yes/No)");
                    String logOutRegChoice = input.next();
                    if(!(logOutRegChoice.equalsIgnoreCase("yes")||logOutRegChoice.equalsIgnoreCase("no"))) {
                        System.out.println("That is not a valid option!");
                    }
                    else {
                        initialScreen();
                    }
                }
            }
            else if(regularUserChoice.equals("5")) {
                printCertificateScreen();
            }
            else {
                System.out.print("That is not a valid choice!");
            }
            
        }
    }

    //Author Class
    public static void authorScreen() {
        
        boolean AuthorScreenValid = false;
        while(!AuthorScreenValid) {
            System.out.println("\n**** Author Main Menu ****\n\n1. View Account Information\n2. Create Course\n3. View/Edit Course(s)\n4. Logout\n\nPlease enter what you would like to do:\n");
            String authorChoice = input.next();
            
            //View account information
            if(authorChoice.equals("1")) {
                AuthorScreenValid = true;
                
                boolean viewAccountInformation = false;
                while(!viewAccountInformation) {
                    System.out.println("\n*** Account Information ***\n\n");
                    System.out.println(currentUser.toString());
                    System.out.println("\nType 'back' to go to the previous page");
                    String authorBack4 = input.next();
                    if(!(authorBack4.equalsIgnoreCase("back"))) {
                        System.out.println("That is not a valid choice");
                    }
                    else {
                        authorScreen();
                    }
                }
            }
            //Make a course
            else if(authorChoice.equals("2")) {
                AuthorScreenValid = true;

                boolean makeCourse = false;
                while(!makeCourse) {
                    //Course variables
                    System.out.println("\n*** Make a course ***\n\n");
                    System.out.println("Title:\n");
                    String courseTitle = input.next();
                    input.nextLine();
                    System.out.println("Language:\n");
                    String courseLanguage = input.next();
                    input.nextLine();
                    System.out.println("Author's Username:\n");
                    String courseAuthor = input.next();
                    input.nextLine();
                    System.out.println("\nDate ('MM/DD/YY'):");
                    String courseDate = input.next();
                    input.nextLine();
                    System.out.println("\nDifficulty:");
                    String courseDifficulty = input.next();
                    input.nextLine();
                    System.out.println("Summary: ");
                    String courseSummary = input.next();
                    input.nextLine();
                    //Module variables
                    
                    System.out.println("\n*** Add a module ***\n\n");
                    System.out.println("Module title:\n");
                    String moduleTitle = input.next();
                    input.nextLine();
                    Module tempModule = new Module();
                    tempModule.setPrompt(moduleTitle);
                    // Lesson Variables
                    Lesson tempLesson = new Lesson("","","");
                    System.out.println("Lesson Title:\n");
                    String lessonTitle = input.next();
                    input.nextLine();
                    tempLesson.setTitle(lessonTitle);
                    System.out.println("Write out the lesson content:\n");
                    String lessonContent = input.next();
                    input.nextLine();
                    tempLesson.setContents(lessonContent);
                    System.out.println("Lesson Number:\n");
                    String lessonNumber = input.next();
                    input.nextLine();
                    tempLesson.setLessonNumber(lessonNumber);
                    

                    tempModule.addLesson(tempLesson);


                    //Quiz variables
                    System.out.println("\n*** Add a quiz ***\n\n");
                    System.out.println("Question:\n");
                    String quizQuestion = input.next();
                    input.nextLine();
                    System.out.println("Option a.\n");
                    String quizOptionA = input.next();
                    input.nextLine();
                    System.out.println("Option b.\n");
                    String quizOptionB = input.next();
                    input.nextLine();
                    System.out.println("Option c.\n");
                    String quizOptionC = input.next();
                    input.nextLine();
                    System.out.println("Option d.\n");
                    String quizOptionD = input.next();
                    input.nextLine();
                    System.out.println("Correct Answer (this must be an integer, 1 = Option A, 2 = Option B, 3 = Option C, 4 = Option D)\n");
                    int correctAnswer = input.nextInt();
                    Quiz tempQuiz = new Quiz();
                    ArrayList<String> questionChoices = new ArrayList<String>();
                    questionChoices.add(quizOptionA);
                    questionChoices.add(quizOptionB);
                    questionChoices.add(quizOptionC);
                    questionChoices.add(quizOptionD);
                    tempQuiz.setPrompt(quizQuestion);
                    tempQuiz.setChoices(questionChoices);
                    tempQuiz.setCorrectAnswer(correctAnswer);
                    tempQuiz.setNumOfChoices(4);
                    //Set variables to method
                    Course newCourse = new Course();
                    
                    newCourse.setAuthor((Author)UserList.getInstance().getUserByUsername(courseAuthor));
                    newCourse.setSummary(courseSummary);
                    newCourse.setTitle(courseTitle);
                    newCourse.setLanguage(courseLanguage);
                    newCourse.setCourseID(UUID.randomUUID());
                    newCourse.setDateCreated(courseDate);
                    newCourse.setDifficulty(courseDifficulty);
                    courseList.addCourse(newCourse);
                    DataWriter.saveCourses();
                    makeCourse = true;
                }
                authorScreen();
            }
                  //Edit course(s)
                  else if (authorChoice.equals("3")) {
                    AuthorScreenValid = true;
                    //CourseSystemApplication app = new CourseSystemApplication();
        
                    boolean editCourse = false;
                    while (!editCourse) {
                         System.out.println("\n*** Edit Course(s) ***\n\n");
                         for(int i=0;i<courseList.getCourses().size();++i) {
                            System.out.println(courseList.getCourses().get(i));
                         }
                         System.out.println("\nEnter the title of the course you would like to edit (or enter 'b' to go back):\n");
                         String editCourseChoice = input.next();
                         input.nextLine();
                         if(!(editCourseChoice.equalsIgnoreCase("b"))) {
                             editCourse = true;
        
                             boolean editCourseContentOrModule = false;
                             while (!editCourseContentOrModule) {
                                System.out.println("\nWhat would you like to do:\n\n1. Edit Course details\n2. Edit Module \n3. Add Lesson to a Module \n(or enter 'b' to go back)");
                                String courseDetailsOrModule = input.next();
                                if (courseDetailsOrModule.equals("1")) {
                                 //Filler
                                } else if (courseDetailsOrModule.equals("2")) {
                                    editCourseContentOrModule = true;
        
                                     boolean editModule = false;
                                     while (!editModule) {
                                     System.out.println("\n*** Edit Module ***\n\n");
                                      //Print modules
                                      for(int i=0;i<courseList.getCourses().size();++i) {
                                        Course tempCourse = courseList.getCourses().get(i);
                                        for(int j=0;j<tempCourse.getModules().size();++j) {
                                            System.out.println(tempCourse.getModules().get(j).getPrompt());
                                        }
                                      }
                                     System.out.println("\n\nEnter the title of the module you would like to edit (or enter 'b' to go back):");
                                     String selectModuleToEdit = input.next();
                                     if (!(selectModuleToEdit.equals("b"))) {
                                        editModule = true;
        
                                        boolean editModuleContentsChoice = false;
                                        while (!editModuleContentsChoice) {
                                            System.out.println("\nWould you like to edit/add the:\n\n1. Prompt\n2. Lesson\n3. Quiz (or enter 'b' to go back)\n");
                                            String modulePLQ = input.next();
                                            input.nextLine();
                                            Module tempModule = courseList.getCourseByCourseTitle(editCourseChoice).getCourseByModulePrompt(selectModuleToEdit);
                                            if (modulePLQ.equals("1")) {
                                             //Filler
                                             System.out.println("Enter the new Module Prompt:\n");
                                             String newModulePrompt = input.next();
                                             input.nextLine();
                                             tempModule.setPrompt(newModulePrompt);
                                            } else if (modulePLQ.equals("2")) {
                                                  boolean addOrEditLesson = false;
                                                  while (!addOrEditLesson) {
                                                       System.out.println("\n\nWould you like to\n1. Add lesson\n2. Edit lesson (or enter 'b' to go back)\n");
                                                       String addOrEditLessonInput = input.next();
                                                       if (addOrEditLessonInput.equals("1")) {
                                                            addOrEditLesson = true;
                                                            boolean addLessonBool = false;
                                                            while (!addLessonBool) {
                                                                System.out.println("\nAdd lesson title (or enter 'b' to go back):");
                                                                String addLessonTitle = input.next();
                                                                if (addLessonTitle.equals("b")) {
                                                                   break;
                                                                }
                                                                 System.out.println("\nAdd lesson description:");
                                                                 String addLessonDescription = input.next();
                                                                 System.out.println("Add a lesson number: ");
                                                                 String addLessonNumber = input.next();
                                                                 Lesson tempLesson = new Lesson(addLessonTitle, addLessonDescription, addLessonNumber);
                                                                 tempModule.addLesson(tempLesson);
                                                                 //Navigates back after
                                                                 addLessonBool = true;
                                                                 System.out.println("\nLesson added!");
                                                             }
                                                        } else if (addOrEditLessonInput.equals("2")) {
                                                              //Filler
                                                              System.out.println("Would You Like To Edit the\n1. Lesson Title\n2. Lesson Content\n3. Lesson Number\nEnter 'b' to go back");
                                                              String editPartOfLesson = input.next();
                                                              if(editPartOfLesson.equalsIgnoreCase("b"))
                                                                break;
                                                              else if(editPartOfLesson.equals("1")) {
                                                                System.out.println("Enter the old lesson title you wish to replace");
                                                                String oldLessonTitle = input.next();
                                                                System.out.println("Enter the new Lesson Title");
                                                                String newLessonTitle = input.next();
                                                                tempModule.getLessonByLessonTitle(oldLessonTitle).setTitle(newLessonTitle);
                                                                System.out.println("Your new lesson title has been added!");
                                                              }
                                                        } else if (addOrEditLessonInput.equals("b")) {
                                                              break;
                                                        } else {
                                                             System.out.println("That's not a valid choice!");
                                                        }
                                                    }   
                                            } else if (modulePLQ.equals("3")) {
                                                 //Filler
                                            } else if (modulePLQ.equals("b")) {
                                                  break;
                                            } else {
                                                 System.out.println("That is not a valid option!");
                                            }
                                        }
                                    } else if (selectModuleToEdit.equals("b")) {
                                         break;
                                    } else {
                                         System.out.println("That's not a valid choice");
                                    }
                                    }
                                }  
                                else if(courseDetailsOrModule.equals("3")) {
                                    editCourseContentOrModule = true;
                                    boolean addLesson = false;

                                    while(!addLesson) {
                                        for(int i=0;i<courseList.getCourses().size();++i) {
                                            Course tempCourse = courseList.getCourses().get(i);
                                            for(int j=0;j<tempCourse.getModules().size();++j) {
                                                Module tempModule = tempCourse.getModules().get(j);
                                                for(int k=0;k<tempModule.getLessons().size();++k) {
                                                    System.out.println(" - " + tempModule.getLessons().get(k).getTitle());
                                                }
                                                System.out.println(tempModule.getPrompt());
                                            }
                                        }
                                        System.out.println("Enter the Title of the Module you would like to add a lesson to\n(or enter 'b' to go back)");
                                        String tempModuleTitle = input.nextLine();
                                        input.nextLine();
                                        if(tempModuleTitle.equals("b")) {
                                            addLesson = true;
                                            break;
                                        }
                                        Module tempModule = new Module();
                                        for(int i=0;i<courseList.getCourses().size();++i) {
                                            Course tempCourse = courseList.getCourses().get(i);
                                            for(int j=0;j<tempCourse.getModules().size();++j) {
                                                if(tempCourse.getModules().get(j).getPrompt().equals(tempModuleTitle)) {
                                                    tempModule = tempCourse.getModules().get(i);
                                                    break;
                                                }
                                            }
                                            //tempModule = courseList.getCourseByCourseTitle(editCourseChoice).getModules().get(i);
                                        }
                                        
                                        System.out.println("Enter the title of the new lesson you would like to add");
                                        String tempLessonTitle = input.nextLine();
                                        
                                        System.out.println("Enter the contents of the new lesson you would like to add");
                                        String tempLessonContents = input.nextLine();

                                        System.out.println("Enter the lesson number of the new lesson you would like to add");
                                        String tempLessonNumber = input.nextLine();

                                        for(int i=0;i<courseList.getCourses().size();++i) {
                                            Course tempCourse = courseList.getCourses().get(i);
                                            for(int j=0;j<tempCourse.getModules().size();++j) {
                                                if(tempCourse.getModules().get(j).getPrompt().equalsIgnoreCase(tempModuleTitle)) {
                                                    Lesson tempLesson = new Lesson(tempLessonTitle, tempLessonContents, tempLessonNumber);
                                                    /*ArrayList<Module> tempModules = tempCourse.getModules();
                                                    ArrayList<Lesson> tempLessons = tempModule.getLessons();
                                                    for(int k=0;k<tempModules.size();++k) {
                                                        tempLessons.add(tempModules.get(j).getLessons().get(k));
                                                    }
                                                    tempModule.addLessons(tempLessons);
                                                    tempModule.addLesson(tempLesson);
                                                    tempModules.add(tempModule);
                                                    //tempCourse.addModule(tempModule);
                                                    tempCourse.setModules(tempModules);
                                                    //tempCourse.setModules(tempModules);
                                                    courseList.addCourse(tempCourse);*/
                                                    tempModule.addLesson(tempLesson);
                                                    //tempCourse.addModule(tempModule);
                                                    //courseList.addCourse(tempCourse);
                                                    break;
                                                }
                                            }
                                        }
                                        System.out.println("New Modules and Lessons");
                                        for(int i=0;i<courseList.getCourses().size();++i) {
                                            Course tempCourse = courseList.getCourses().get(i);
                                            for(int j=0;j<tempCourse.getModules().size();++j) {
                                                Module module = tempCourse.getModules().get(j);
                                                System.out.println(module.getPrompt());
                                                for(int k=0;k<module.getLessons().size();++k) {
                                                    System.out.println(" - " + module.getLessons().get(k));
                                                }
                                            }
                                        }
                                        

                                        addLesson = true;
                                    }
                                }
                             }
                              
                            editCourse = true;
                        }
                    
                        authorScreen();
                    }
                    }
            //Logout
            else if(authorChoice.equals("4")) {
                AuthorScreenValid = true;

                boolean logOut = false;
                while(!logOut) {
                    System.out.println("\n*** Logout ***\n\nAre you sure you want to logout? (Yes/No)");
                    String logOutChoice = input.next();
                    if(!(logOutChoice.equalsIgnoreCase("yes")||logOutChoice.equalsIgnoreCase("no"))) {
                        System.out.println("That is not a valid option!");
                    }
                    else {
                        //Logout Method
                        System.out.println("\nYou have successfully logged out!");
                        initialScreen();
                    }
                }
            }
            //User error
            else {
                System.out.print("That is not a valid choice!");
            }
        }
    }

    //incomplete 
    public static void printCertificateScreen() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\nPlease enter the course language:\n");
            String courseLanguage = reader.readLine();
            System.out.print("\nPLease enter your name:\n");
            String userName = reader.readLine();
            FileWriter writer = new FileWriter("Certificate.txt");
            writer.write("\tCONGRATULATIONS\n\t---------------\n\n\tThank you "+userName+", for successfully completing the "+courseLanguage+" course!\n\n\tYou are now certified in "+courseLanguage+"!");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

    //Print Module Information (Not comments or Quiz)
    public void printModuleInformation(Course course) {
        for(int i=0;i<course.getModules().size();++i) {
            System.out.println(course.getModules().get(i).toString());
        }
    }

    //works 
    public static void login() {
        System.out.println("Please enter your information below\n--------------------------------");
        System.out.println("\nUsername:");
        String username = input.next();
        System.out.println("\nPassword:");
        String password = input.next();

        //CourseSystemApplication app = new CourseSystemApplication();
        if (app.login(username,password)) {
            if (app.getCurrentUser().getType().equalsIgnoreCase("student")) {
                currentUser = UserList.getInstance().getUserByUsername(username);
                studentScreen();
            } 
            else {
                currentUser = UserList.getInstance().getUserByUsername(username);
                authorScreen();
            }
        } 
        else { 
            System.out.println("Incorrect login. Please try again. \nIf you would like to try and log in again 1, otherwise enter anything else.");
            int back = input.nextInt();
            input.nextLine();
            if (back == 1) {
                login();
            }
            else if (back != 1) {
                initialScreen();
            }
            
        }
    }
    //works
    public static void signUp() {
        
        System.out.println("\nPlease enter your information below\n-----------------------------------");
        System.out.println("\nFirst name:");
        String firstName = input.next();
        System.out.println("\nLast name:");
        String lastName = input.next();
        System.out.println("\nDate of birth ('MM/DD/YYYY'):");
        String userBirthday = input.next();
        System.out.println("\nUsername:");
        String chosenUserName = input.next();
        System.out.println("\nPassword:");
        String chosenPassword = input.next();
        System.out.println("\nEmail:");
        String chosenEmail = input.next();
        System.out.println("\nAccount type:\n1.Author\n2.Student\n");
        int accountType = input.nextInt();
        String realAccountType;
        if (accountType == 1) {
            realAccountType = "Author";
        }
        else {
            realAccountType = "Student";
        }


        UUID userUUID = UUID.randomUUID();

        ArrayList<Course> userCourses = null;

        userList.addUser(userUUID, realAccountType, firstName, lastName, chosenUserName, chosenPassword, chosenEmail, userBirthday, userCourses);
        System.out.println("\n\n**** Create an Account ****\n\n");
    }

    public static void populateUserResults(Results userResults) {
        userResults.setUUID(currentUser.getUUID());
        ArrayList<Double> tempGrades = new ArrayList<Double>();
        for(int i=0;i<currentUser.getCurrentCourses().size();++i) {
            Course tempCourse = currentUser.getCurrentCourses().get(i);
            for(int j=0;j<tempCourse.getModules().size();++j) {
                Module tempModule = tempCourse.getModules().get(i);
                tempGrades.add(tempModule.getGrade());
            }
        }
        userResults.setGrades(tempGrades);
    }

    public static void main(String[] args) {
        initialScreen();
        populateUserResults(userResults);
    }

}
