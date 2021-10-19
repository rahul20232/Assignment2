
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Date;

interface Bagpack{
    //void view_material();
//    void assessments();
    void view_comments();
    void add_comments(String com,Date d);
//    void Logout();
}
class material extends Instructor{
    private String slide_material;
    private int num_slides;
    ArrayList contents = new ArrayList();
    private String lecture_material;
    private String filename;
    private Date d;
    private String name;

    public material(String slide_material,int num,ArrayList contents,Date d,String name){
        this.slide_material = slide_material;
        this.num_slides = num;
        this.contents = contents;
        this.d = d;
        this.name = name;
    }
    public material(String lecture_material, String filename,Date d,String name){
        this.lecture_material = lecture_material;
        this.filename = filename;
        this.d = d;
        this.name = name;
    }
    public void display_1(){
        System.out.println("Title:"+this.slide_material);
        for(int i = 0;i<this.num_slides;i++){
            System.out.println("Slide "+i+1+":"+contents.get(i));
        }
        System.out.println("Number of slides:"+this.num_slides);
        System.out.println("Date of upload: "+d.toString());
        System.out.println("Uploaded by: "+this.name);
    }
    public void display_2(){
        System.out.println("Title of video:"+this.lecture_material);
        System.out.println("Video File:"+this.filename);
        System.out.println("Date of upload: "+d.toString());
        System.out.println("Uploaded by: "+this.name);
    }
    public boolean getSlideMaterial(){
        if(slide_material!=null){
            return true;
        }
        return false;
    }
    public boolean getVideoMaterial(){
        if(lecture_material!=null){
            return true;
        }
        return false;
    }
}
class slide extends material{

    public slide(String slide_material, int num, ArrayList contents,Date d,String name) {
        super(slide_material, num, contents,d,name);
    }

}
class lecture extends material{
//    private String lecture_material;
//    private String filename;
//    private String name;
    public lecture(String mat, String x,Date d,String name){
        super(mat,x,d,name);
//        this.lecture_material = mat;
//        this.filename = x;
    }


}
class Instructor implements Bagpack{
    private String name;
    //private String slide_material;
    private String lecture_material;
    //private int num_slides;
    private String filename;
    private int ID;
    //ArrayList contents = new ArrayList();
    ArrayList<Comments> c = new ArrayList<Comments>();

    public Instructor(String name, int ID){
        this.name = name;
        this.ID = ID;
    }

    Instructor() {
    }

    String getName(){
        return name;
    }

    @Override
    public void view_comments() {

        for(int i = 0;i< c.size();i++){
            System.out.println(c.get(i).getComment()+"-"+c.get(i).getName());
            System.out.println(c.get(i).getDate());
        }

    }

        material add_class_material_1(String mat,int num, ArrayList contents,Date d) {
            material m = new material(mat,num,contents,d,this.getName());
            return m;
        }
        material add_class_material_2(String mat, String x,Date d){
            material m = new material(mat,x,d,this.getName());
            return m;
        }

        Assignment add_Assignment(String ps,int MM, int ID){
            Assignment a = new Assignment(ps,MM, ID);
            return a;
        }
        Quiz add_Quiz(String ps, int ID){
            Quiz q = new Quiz(ps, ID);
            return q;
        }

    public int getID(){
        return this.ID;
    }

    @Override
    public void add_comments(String comment, Date d){
        Comments com = new Comments(comment, d, this.name);
        c.add(com);
    }

}
interface Assessments{
    public void close();
    public int getID();
    public String getPS();
    public boolean isOpen();

}
class Assignment implements Assessments{
    private boolean open = true;
    private String problem_statement;
    private int Max_Marks;
    private int ID;
    private boolean submit = false;
    ArrayList<ArrayList> arr = new ArrayList();

    public Assignment(String ps,int MM, int ID){
        this.problem_statement = ps;
        this.Max_Marks = MM;
        this.ID = ID;
    }

    @Override
    public String getPS(){
        return this.problem_statement;
    }
    public int getMax_Marks(){
        return this.Max_Marks;
    }
    @Override
    public int getID(){
        return ID;
    }
    @Override
    public void close(){this.open = false;}
    @Override
    public boolean isOpen(){
        if(this.open){
            return true;
        }
        return false;
    }


    public void set_submission(String name, String filename,int ID, boolean graded, int Marks){
        ArrayList arr1 = new ArrayList();
        arr1.add(name);
        arr1.add(filename);
        arr1.add(ID);
        arr1.add(false);
        arr1.add(Marks);
        arr1.add(true);
        arr.add(arr1);
    }
    public boolean isSubmit(){
        if(this.submit){
            return true;
        }
        return false;
    }

}
class Quiz implements Assessments{
    private boolean open = true;
    private String question;
    private int ID;
    private boolean submit = false;
    ArrayList<ArrayList> arr = new ArrayList();
    public Quiz(String ps, int ID){
        this.question = ps;
        this.ID = ID;
    }

    public void close(){
        this.open = false;
    }
    public boolean isOpen(){
        if(this.open){
            return true;
        }
        return false;
    }
    public String getPS(){
        return this.question;
    }
    public int getID(){
        return this.ID;
    }
    public void set_submission(String name,String filename,int ID, boolean graded, int Marks){
        ArrayList arr1 = new ArrayList();
        arr1.add(name);
        arr1.add(filename);
        arr1.add(ID);
        arr1.add(graded);
        arr1.add(Marks);
        arr1.add(true);
        arr.add(arr1);
        this.submit = true;
    }
    public boolean isSubmit(){
        if(this.submit){
            return true;
        }
        return false;
    }
}
class Student implements Bagpack {
    private String name;
    private int ID;
    ArrayList<Comments> c = new ArrayList<Comments>();

    public Student(String name, int ID){
        super();
        this.name = name;
        this.ID = ID;
    }


    public String getName(){
        return this.name;
    }
    @Override
    public void view_comments() {
        for(int i = 0;i< c.size();i++){
            System.out.println(c.get(i).getComment()+"-"+c.get(i).getName());
            System.out.println(c.get(i).getDate());
        }

    }
    public int getID(){
        return this.ID;
    }

    @Override
    public void add_comments(String comment, Date d){
        Comments com = new Comments(comment, d, this.name);
        c.add(com);
    }

}
class Comments{
    private String comment;
    private Date d;
    private String name;
    public Comments(String com, Date date, String n){
        this.comment = com;
        this.d = date;
        this.name = n;
    }
    public String getComment(){
        return this.comment;
    }
    public String getDate(){
        return this.d.toString();
    }
    public String getName(){
        return this.name;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Comments> comments = new ArrayList<>();
        ArrayList<material> m = new ArrayList<>();
        ArrayList<Assignment> ass1 = new ArrayList<>();
        ArrayList<Quiz> q = new ArrayList<>();
        ArrayList<Instructor> instructors = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();

        int j = 0;
        System.out.println("Enter no. of instructors: ");
        int a = sc.nextInt();

        for(int i = 0;i<a;i++){
            Instructor i1 = new Instructor(("I"+i),i);
            instructors.add(i1);
        }
        System.out.println("Enter no. of students: ");
        int b = sc.nextInt();
        for(int i = 0;i<b;i++){
            Student s1 = new Student(("S"+i),i);
            students.add(s1);

        }
        while(true){
            printInitialMenu();
            int input_1 = sc.nextInt();
            if(input_1==1){
                listInstructors(a);
                System.out.print("Choose Id:");
                int input_2 = sc.nextInt();
                for(int i = 0;i<instructors.size();i++){
                    if(instructors.get(i).getID()==input_2){
                        boolean flag1 = true;
                        while(flag1){
                            System.out.println("Welcome "+instructors.get(i).getName());
                            InstructorMenu();
                            int input_3 = sc.nextInt();
                            if(input_3==1){
                                System.out.println("1. Add Lecture Slide");
                                System.out.println("2. Add Lecture Video");
                                int input_4 = sc.nextInt();
                                if(input_4==1){
                                    System.out.print("Enter topic of slides:");
                                    sc.nextLine();
                                    String a1 = sc.nextLine();
                                    System.out.print("Enter number of slides:");
                                    int b1 = sc.nextInt();
                                    ArrayList contents = new ArrayList();
                                    for(int r = 0;r<b1;r++){
                                        System.out.print("Content of slide"+(r+1)+":");
                                        sc.nextLine();
                                        contents.add(sc.nextLine());
                                    }
                                    Date d = new Date();
                                    m.add(instructors.get(i).add_class_material_1(a1,b1,contents,d));
                                }
                                if(input_4==2){
                                    System.out.print("Enter topic of video:");
                                    sc.nextLine();
                                    String a1 = sc.nextLine();
                                    System.out.print("Enter filename of video:");
                                    String b1 = sc.next();
                                    if(b1.substring(b1.length()-4,b1.length()).equals(".mp4")){
                                        Date d = new Date();
                                        m.add(instructors.get(i).add_class_material_2(a1,b1,d));
                                    }
                                    else{
                                        System.out.println("Filename not valid");
                                    }
                                }
                            }
                            if(input_3==2){
                                System.out.println("1. Add Assignment");
                                System.out.println("2. Add Quiz");
                                int input_4 = sc.nextInt();
                                if(input_4==1){
                                    System.out.print("Enter problem statement:");
                                    sc.nextLine();
                                    String s1 = sc.nextLine();
                                    System.out.print("Enter max marks: ");
                                    int max = sc.nextInt();
                                    int ID = j;
                                    ass1.add(instructors.get(i).add_Assignment(s1,max,ID));
                                    j += 1;

                                }
                                if(input_4==2){
                                    System.out.print("Enter quiz question: ");
                                    sc.nextLine();
                                    String s1 = sc.nextLine();
                                    int ID = j;
                                    q.add(instructors.get(i).add_Quiz(s1,ID));
                                    j+=1;
                                }
                            }
                            if(input_3==3){
                                for(int r = 0;r<m.size();r++){
                                    if(m.get(r).getSlideMaterial()){
                                        m.get(r).display_1();
                                        System.out.println();
                                    }
                                    if(m.get(r).getVideoMaterial()){
                                        m.get(r).display_2();
                                        System.out.println();
                                    }
                                }
                            }
                            if(input_3==4){
                                for(int r = 0;r<ass1.size();r++){
                                    if(ass1.get(r).isOpen()){
                                        System.out.println("ID: "+ass1.get(r).getID()+" Assignment: "+ass1.get(r).getPS()+" Max Marks: "+ass1.get(r).getMax_Marks());
                                        System.out.println("----------------");
                                    }
                                }
                                for(int r = 0;r<q.size();r++){
                                    if(q.get(r).isOpen()){
                                        System.out.println("ID: "+q.get(r).getID()+" Question: "+q.get(r).getPS());
                                        System.out.println("----------------");
                                    }
                                }
                            }
                            if(input_3==5){
                                for(int r = 0;r<ass1.size();r++){
                                    if(ass1.get(r).isOpen()){
                                        System.out.println("ID: "+ass1.get(r).getID()+" Assignment: "+ass1.get(r).getPS()+" Max Marks: "+ass1.get(r).getMax_Marks());
                                        System.out.println("----------------");
                                    }
                                }
                                for(int r = 0;r<q.size();r++){
                                    if(q.get(r).isOpen()){
                                        System.out.println("ID: "+q.get(r).getID()+" Question: "+q.get(r).getPS());
                                        System.out.println("----------------");
                                    }
                                }
                                System.out.print("Enter ID of assessment to view submissions:");
                                int a1 = sc.nextInt();
                                for(int r = 0;r<ass1.size();r++){
                                    if(ass1.get(r).getID()==a1){
                                        for(int p = 0;p<ass1.get(r).arr.size();p++){
                                            if(!(boolean)ass1.get(r).arr.get(p).get(3)){
                                                System.out.println(ass1.get(r).arr.get(p).get(2)+". "+ass1.get(r).arr.get(p).get(0));
                                            }
                                        }
                                    }
                                }
                                for(int r = 0;r<q.size();r++){
                                        if(q.get(r).getID()==a1){
                                            for(int p = 0;p<q.get(r).arr.size();p++){
                                                if(!(boolean)q.get(r).arr.get(p).get(3)){
                                                    System.out.println(q.get(r).arr.get(p).get(2)+". "+q.get(r).arr.get(p).get(0));
                                                }
                                            }
                                        }
                                }
                                int n1 = sc.nextInt();
                                for(int r = 0;r<ass1.size();r++){
                                        if(ass1.get(r).getID()==a1){
                                            for(int p = 0;p<ass1.get(r).arr.size();p++){
                                                if(!(boolean)ass1.get(r).arr.get(p).get(3)){
                                                    int x = (int) ass1.get(r).arr.get(p).get(2);
                                                    if(x==n1){
                                                        String y = (String) ass1.get(r).arr.get(p).get(1);
                                                        System.out.println("Submission:"+y);
                                                        System.out.println("--------------");
                                                        System.out.println("Max Marks: "+ass1.get(r).getMax_Marks());
                                                        System.out.print("Marks scored: ");
                                                        int marks = sc.nextInt();
                                                        ass1.get(r).arr.get(p).set(4,marks);
                                                        ass1.get(r).arr.get(p).set(3,true);
                                                        ass1.get(r).arr.get(p).add(instructors.get(i).getName());
                                                    }
                                                }
                                            }
                                        }
                                }
                                for(int r = 0;r<q.size();r++){
                                        if(q.get(r).getID()==a1){
                                            for(int p = 0;p<q.get(r).arr.size();p++){
                                                if(!(boolean)q.get(r).arr.get(p).get(3)){
                                                    int x = (int) q.get(r).arr.get(p).get(2);
                                                    if(x==n1){
                                                        String y = (String) q.get(r).arr.get(p).get(1);
                                                        System.out.println("Submission:"+y);
                                                        System.out.println("--------------");
                                                        q.get(r).arr.get(p).set(3,true);
                                                    }
                                                }
                                            }
                                        }
                                }



                            }
                            if(input_3==6){
                                System.out.println("List of Open Assignments:");
                                for(int r = 0;r<ass1.size();r++){
                                    if(ass1.get(r).isOpen()){
                                        System.out.println("ID: "+ass1.get(r).getID()+" Assignment: "+ass1.get(r).getPS()+" Max Marks: "+ass1.get(r).getMax_Marks());
                                        System.out.println("----------------");
                                    }
                                }
                                for(int r = 0;r<q.size();r++){
                                    if(q.get(r).isOpen()){
                                        System.out.println("ID: "+q.get(r).getID()+" Question: "+q.get(r).getPS());
                                        System.out.println("----------------");
                                    }
                                }
                                System.out.print("Enter ID of Assignment to close: ");
                                int n = sc.nextInt();
                                for(int r = 0;r<ass1.size();r++){
                                    if(ass1.get(r).isOpen()){
                                        if(ass1.get(r).getID()==n){
                                            ass1.get(r).close();
                                        }
                                    }
                                }
                                for(int r = 0;r<q.size();r++){
                                    if(q.get(r).isOpen()){
                                        if(q.get(r).getID()==n){
                                            q.get(r).close();
                                        }
                                    }
                                }
                            }
                            if(input_3==7){
                                for(int r = 0; r< comments.size(); r++){
                                    System.out.println(comments.get(r).getComment()+"-"+comments.get(r).getName());
                                    System.out.println(comments.get(r).getDate());
                                }
                            }
                            if(input_3==8){
                                System.out.print("Enter comment: ");
                                sc.nextLine();
                                String com = sc.nextLine();
                                Date d = new Date();
                                Comments c = new Comments(com,d,instructors.get(i).getName());
                                comments.add(c);
                            }
                            if(input_3==9){
                                flag1 = false;
                            }
                        }
                    }
                }
                }
            if(input_1==2){
                listStudents(b);
                System.out.print("Choose Id:");
                int input_2 = sc.nextInt();
                for(int r = 0;r<students.size();r++){
                    if(students.get(r).getID()==input_2){
                        System.out.println("Welcome "+students.get(r).getName());
                        boolean flag1 = true;
                        while(flag1){
                            StudentMenu();
                            int input_3 = sc.nextInt();
                            if(input_3==1){
                                for(int i = 0;i<m.size();i++){
                                    if(m.get(i).getSlideMaterial()){
                                        m.get(i).display_1();
                                        System.out.println();
                                    }
                                    if(m.get(i).getVideoMaterial()){
                                        m.get(i).display_2();
                                        System.out.println();
                                    }
                                }
                            }
                            if(input_3==2){
                                for(int i = 0;i<ass1.size();i++){
                                    if(ass1.get(i).isOpen()){
                                        System.out.println("ID: "+ass1.get(i).getID()+" Assignment: "+ass1.get(i).getPS()+" Max Marks: "+ass1.get(i).getMax_Marks());
                                        System.out.println("----------------");
                                    }
                                }
                                for(int i = 0;i<q.size();i++){
                                    if(q.get(i).isOpen()){
                                        System.out.println("ID: "+q.get(i).getID()+" Question: "+q.get(i).getPS());
                                        System.out.println("----------------");
                                    }
                                }
                            }
                            if(input_3==3){
                                boolean flag = false;
                                System.out.println("Pending assessments");
                                for(int i = 0;i<ass1.size();i++){
                                    if(ass1.get(i).isOpen()){
                                        boolean submitted = false;
                                        for(int p = 0;p<ass1.get(i).arr.size();p++){
                                            if((int)ass1.get(i).arr.get(p).get(2)==students.get(r).getID()){
                                                if((boolean) ass1.get(i).arr.get(p).get(5)==false){
                                                    System.out.println("ID: "+ass1.get(i).getID()+" Assignment: "+ass1.get(i).getPS()+" Max Marks: "+ass1.get(i).getMax_Marks());
                                                    flag = true;
                                                }
                                                submitted = true;
                                            }
                                        }
                                        if(!submitted){
                                            System.out.println("ID: "+ass1.get(i).getID()+" Assignment: "+ass1.get(i).getPS()+" Max Marks: "+ass1.get(i).getMax_Marks());
                                            flag = true;
                                        }
                                    }
                                }
                                for(int i = 0;i<q.size();i++){
                                    if(q.get(i).isOpen()){
                                        boolean submitted_1 = false;
                                        for(int p = 0;p<q.get(i).arr.size();p++){
                                            if((int)q.get(i).arr.get(p).get(2)==students.get(r).getID()){
                                                if((boolean) q.get(i).arr.get(p).get(5)==false){
                                                    System.out.println("ID: "+q.get(i).getID()+" Question: "+q.get(i).getPS());
                                                    flag = true;
                                                }
                                                submitted_1 = true;
                                            }

                                        }
                                        if(!submitted_1){
                                            System.out.println("ID: "+q.get(i).getID()+" Question: "+q.get(i).getPS());
                                            flag = true;
                                        }
                                    }
                                }
                                if(flag){
                                    System.out.print("Enter ID of assessment: ");
                                    int n = sc.nextInt();
                                    for(int i = 0;i<ass1.size();i++){
                                        if(ass1.get(i).isOpen()){
                                            if(ass1.get(i).getID()==n){
                                                System.out.print("Enter filename of assignment: ");
                                                String s = sc.next();
                                                if(s.substring(s.length()-4,s.length()).equals(".zip")){
                                                    String name = students.get(r).getName();
                                                    ass1.get(i).set_submission(name,s,students.get(r).getID(),false,0);
                                                }
                                                else{
                                                    System.out.println("Filename not valid");
                                                }
                                            }
                                        }
                                    }
                                    for(int i = 0;i<q.size();i++){
                                        if(q.get(i).isOpen()){
                                            if(q.get(i).getID()==n){
                                                System.out.print(q.get(i).getPS());
                                                String s = sc.next();
                                                String name = students.get(r).getName();
                                                q.get(i).set_submission(name,s,students.get(r).getID(),false,0);
                                            }
                                        }
                                    }
                                }
                                if(!flag){
                                    System.out.println("No pending assessments");
                                }
                            }
                            if(input_3==4){
                                System.out.println("Ungraded submissions");
                                for(int i = 0;i< ass1.size();i++){
                                    for(int l = 0;l<ass1.get(i).arr.size();l++){
                                        if((int) ass1.get(i).arr.get(l).get(2)==students.get(r).getID()){
                                            if(!(boolean)ass1.get(i).arr.get(l).get(3)){
                                                System.out.println(ass1.get(i).arr.get(l).get(1));
                                            }
                                        }
                                    }
                                }
                                for(int i = 0;i< q.size();i++){
                                    for(int l = 0;l<q.get(i).arr.size();l++){
                                        if((int) q.get(i).arr.get(l).get(2)==students.get(r).getID()){
                                            if(!(boolean)q.get(i).arr.get(l).get(3)){
                                                System.out.println(q.get(i).arr.get(l).get(1));
                                            }
                                        }
                                    }
                                }
                                System.out.println("Graded submissions");
                                for(int i = 0;i< ass1.size();i++){
                                    for(int l = 0;l<ass1.get(i).arr.size();l++){
                                        if((int) ass1.get(i).arr.get(l).get(2)==students.get(r).getID()){
                                            if((boolean)ass1.get(i).arr.get(l).get(3)){
                                                System.out.println(ass1.get(i).arr.get(l).get(1));
                                                System.out.println("Marks scored: "+ass1.get(i).arr.get(l).get(4));
                                                System.out.println("Graded by: "+ass1.get(i).arr.get(l).get(6));
                                            }
                                        }
                                    }
                                }
                                for(int i = 0;i< q.size();i++){
                                    for(int l = 0;l<q.get(i).arr.size();l++){
                                        if((int) q.get(i).arr.get(l).get(2)==students.get(r).getID()){
                                            if((boolean)q.get(i).arr.get(l).get(3)){
                                                System.out.println(q.get(i).arr.get(l).get(1));
                                            }
                                        }
                                    }
                                }
                            }
                            if(input_3==5){
                                for(int i = 0; i< comments.size(); i++){
                                    System.out.println(comments.get(i).getComment()+"-"+comments.get(i).getName());
                                    System.out.println(comments.get(i).getDate());
                                }
                            }
                            if(input_3==6){
                                System.out.print("Enter comment: ");
                                sc.nextLine();
                                String com = sc.nextLine();
                                Date d = new Date();
                                Comments c = new Comments(com,d,students.get(r).getName());
                                comments.add(c);
                            }
                            if(input_3==7){
                                flag1 = false;
                            }
                        }
                    }

                }
            }
            if(input_1==3){
                break;
            }
        }}
    public static void printInitialMenu(){
        System.out.println("Welcome to Backpack" );
        System.out.println("1. Enter as instructor" );
        System.out.println("2. Enter as student" );
        System.out.println("3. Exit" );
    }
    public static void listInstructors(int x){
        System.out.println("Instructors:");
        for(int i = 0;i<x;i++){
            System.out.println(i+" - I"+i);
        }
    }
    public static void listStudents(int x){
        System.out.println("Students:");
        for(int i = 0;i<x;i++){
            System.out.println(i+" - S"+i);
        }
    }
    public static void InstructorMenu(){
        System.out.println("INSTRUCTOR MENU");
        System.out.println("1. Add class material");
        System.out.println("2. Add assessments" );
        System.out.println("3. View lecture materials" );
        System.out.println("4. View assessments" );
        System.out.println("5. Grade assessments" );
        System.out.println("6. Close assessment" );
        System.out.println("7. View comments");
        System.out.println("8. Add comments" );
        System.out.println("9. Logout");
    }
    public static void StudentMenu(){
        System.out.println("STUDENT MENU" );
        System.out.println("1. View lecture materials" );
        System.out.println("2. View assessments" );
        System.out.println("3. Submit assessment" );
        System.out.println("4. View grades" );
        System.out.println("5. View comments" );
        System.out.println("6. Add comments" );
        System.out.println("7. Logout");
    }
}

