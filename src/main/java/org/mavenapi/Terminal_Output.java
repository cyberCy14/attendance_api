// Terminal_Output.java
package org.mavenapi;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Terminal_Output {
    public static FetchOneA fetchOneA;
    public static FetchOneB fetchOneB;
    public static FetchOneC fetchOneC;

    public static FetchAll fetchAll;

    public static void main(String[] args) throws InterruptedException, IOException {
        fetchOneA = new FetchOneA();
        fetchAll = new FetchAll();

        System.out.println("\tCLASS RECORD\n\n");
        System.out.println("\tChoose a section:\n\t[1] Section A\n\t[2] Section B \n\t[3] Section C");
        Scanner opt = new Scanner(System.in);

        String userInput = opt.nextLine();

//---------------------SECTION A-----------------------//

        if (userInput.equals("1")) {
            System.out.println("\t\tSection A\n\t");
            System.out.println("[1] Get All Students\n\t");
            System.out.println("[2] Get one student\n\t");
            String userInput2 = opt.nextLine();

            //Fetching the list of students for Section A
            if (userInput2.equals("1")) {
                System.out.println("Fetching all students of Section A...");
                displayStudentsForSection("section-a");

                //Fetching a searched student of section A
            } else if (userInput2.equals("2")) {
                System.out.println("Search student: ");
                String searchStud = opt.nextLine();
                Student[] students = fetchOneA.fetchDataForStudent(searchStud);

                if (students != null) {
                    if (students.length > 0) {
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    } 
                } else {
                    System.out.println("An error occurred while fetching student data.");
                }
            } else {
                System.out.println("You typed a wrong keyword.");
            }


//---------------------SECTION B-----------------------//

        } else if (userInput.equals("2")) {
            System.out.println("\t\tSection B\n\t");
            System.out.println("[1] Get All Students\n\t");
            System.out.println("[2] Get one student\n\t");
            String userInput2 = opt.nextLine();

             //Fetching the list of students for Section B

            if (userInput2.equals("1")) {
                System.out.println("Fetching all students of Section B...");
                displayStudentsForSection("section-b");

            //Fetching a searched student of section B

            } else if (userInput2.equals("2")) {
                System.out.println("Search student: ");
                String searchStud = opt.nextLine();
                Student[] students = fetchOneB.fetchDataForStudent(searchStud);

                if (students != null) {
                    if (students.length > 0) {
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    } 
                } else {
                    System.out.println("An error occurred while fetching student data.");
                }
            } else {
                System.out.println("You typed a wrong keyword.");
            }

//---------------------SECTION C-----------------------//

        } else if (userInput.equals("3")) {
            System.out.println("\t\tSection C\n\t");
            System.out.println("[1] Get All Students\n\t");
            System.out.println("[2] Get one student\n\t");
            String userInput2 = opt.nextLine();

            //Fetching the list of students for Section C

            if (userInput2.equals("1")) {
                System.out.println("Fetching all students of Section C...");
                displayStudentsForSection("section-c");

             //Fetching a searched student of section C

            } else if (userInput2.equals("2")) {
                System.out.println("Search student: ");
                String searchStud = opt.nextLine();
                Student[] students = fetchOneC.fetchDataForStudent(searchStud);

                if (students != null) {
                    if (students.length > 0) {
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    } 
                } else {
                    System.out.println("An error occurred while fetching student data.");
                }
            } else {
                System.out.println("You typed a wrong keyword.");
            }
        } else {
            System.out.println("You typed a wrong option.");
        }


    }

    private static void displayStudentsForSection(String sectionName) {
        List<Section> sections = fetchAll.fetchDataForAllSections();

        for (Section section : sections) {
            if (sectionName.equals(section.getSectionName())) {
                System.out.println("Displaying students for section: " + sectionName);

                List<Student> students = section.getStudents();

                if (students != null) {
                    for (Student student : students) {
                        System.out.println(student);
                    }
                } else {
                    System.out.println("No students found in this section.");
                }
                break;
            }
        }
    }
}
