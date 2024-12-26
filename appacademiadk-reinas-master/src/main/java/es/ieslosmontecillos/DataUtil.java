package es.ieslosmontecillos;

import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.converter.JsonConverter;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.RestClient;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javax.json.JsonObject;

public class DataUtil {

    private ObservableList<Province> olProvince = FXCollections.observableArrayList();
    private ObservableList<Student> olStudent = FXCollections.observableArrayList();
    private ObservableList<Course> olCourse = FXCollections.observableArrayList();
    private ObservableList<Registration> olRegistration = FXCollections.observableArrayList();

    String host = "localhost";

    /* PROVINCES */
    // Obtain all the provinces
    public void obtainAllProvinces()
    {
        System.out.println("Obtain all provinces.");
        RestClient restClient = RestClient.create()
                .method("GET")
                .host("http://"+host+":8080")
                .path("/api/v1/PROVINCE");
        GluonObservableList<Province> provinces = DataProvider.retrieveList(restClient.createListDataReader(Province.class));

        provinces.addListener(new ListChangeListener<Province>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Province> c) {
                if(c.next()){
                    olProvince.add(c.getList().get(c.getFrom()));
                }

                System.out.println("Provinces list: " + olProvince.get(c.getFrom()).getName());
            }
        });
    }

    // Return the list of all the provinces
    public ObservableList<Province> getOlProvince() {
        return olProvince;
    }

    /* Students */
    public void obtainAllStudents()
    {
        olStudent.clear();
        // Obtain all the Students
        System.out.println("Obtain all students.");
        RestClient restClient = RestClient.create()
                .method("GET")
                .host("http://"+host+":8080")
                .path("/api/v1/STUDENT");
        GluonObservableList<Student> students = DataProvider.retrieveList(restClient.createListDataReader(Student.class));

        students.addListener(new ListChangeListener<Student>() {
            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends Student> c) {
                if(c.next()){
                    olStudent.add(c.getList().get(c.getFrom()));
                }

                System.out.println("Lista estudiantes: " + olStudent.get(c.getFrom()).getName());

            }
        });
    }

    public ObservableList<Student> getOlStudent() {
        return olStudent;
    }

    // we add a Student to the database
    public void addStudent(Student student)
    {
        JsonConverter<Student> converter = new JsonConverter<>(Student.class);
        JsonObject json = converter.writeToJson(student);
        String dataBoy = json.toString();
        System.out.println(dataBoy);
        RestClient restClient = RestClient.create()
                .method("POST")
                .host("http://" + host + ":8080")
                .path("/api/v1/STUDENT")
                .dataString(dataBoy)
                .contentType("application/json");
        GluonObservableObject<Student> newStudent = DataProvider.retrieveObject(restClient.createObjectDataReader(Student.class));
    }

    /* find someone by thei dni */
    public GluonObservableObject<Student>  findStudentByDNI(String dni)
    {
        RestClient restClient = RestClient.create()
                .method("GET")
                .host("http://"+host+":8080")
                .path("/api/v1/STUDENT/" + dni);

        return DataProvider.retrieveObject(restClient.createObjectDataReader(Student.class));
    }

    /* COURSES */
    public void obtainAllCourses()
    {
        olCourse.clear();
        System.out.println("Obtain all courses.");
        RestClient restClient = RestClient.create()
                .method("GET")
                .host("http://"+host+":8080")
                .path("/api/v1/COURSE");
        GluonObservableList<Course> courses = DataProvider.retrieveList(restClient.createListDataReader(Course.class));

        courses.addListener(new ListChangeListener<Course>() {
            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends Course> c) {
                if(c.next()){
                    olCourse.add(c.getList().get(c.getFrom()));
                }

                System.out.println("Course list: " + olCourse.get(c.getFrom()).getName());
            }
        });

    }

    public ObservableList<Course> getOlCourse() {
        return olCourse;
    }

    // we add a course to the database
    public void addCourse(Course course)
    {
        JsonConverter<Course> converter = new JsonConverter<>(Course.class);
        JsonObject json = converter.writeToJson(course);
        String dataBoy = json.toString();
        System.out.println(dataBoy);
        RestClient restClient = RestClient.create()
                .method("POST")
                .host("http://"+host+":8080")
                .path("/api/v1/COURSE")
                .dataString(dataBoy)
                .contentType("application/json");
        GluonObservableObject<Course> newCourse = DataProvider.retrieveObject(restClient.createObjectDataReader(Course.class));
    }


    /* REGISTRATION */
    public void obtainAllRegistrations()
    {
        System.out.println("Obtain all registrations.");

        RestClient restClient = RestClient.create()
                .method("GET")
                .host("http://"+host+":8080")
                .path("/api/v1/REGISTRATION");
        GluonObservableList<Registration> registrations = DataProvider.retrieveList(restClient.createListDataReader(Registration.class));

        registrations.addListener(new ListChangeListener<Registration>() {
            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends Registration> c) {
                if(c.next()){
                    olRegistration.add(c.getList().get(c.getFrom()));
                }

                System.out.println("Registration list" + olRegistration.get(c.getFrom()).getId());
            }
        });
    }

    public ObservableList<Registration> getOlRegistration() {
        return olRegistration;
    }

    public void addRegistration(Registration registration)
    {
        JsonConverter<Registration> converter = new JsonConverter<>(Registration.class);
        JsonObject json = converter.writeToJson(registration);
        String dataBoy = json.toString();
        System.out.println(dataBoy);

        RestClient restClient = RestClient.create()
                .method("POST")
                .host("http://"+host+":8080")
                .path("/api/v1/REGISTRATION")
                .dataString(dataBoy)
                .contentType("application/json");
        GluonObservableObject<Registration> newRegistration = DataProvider.retrieveObject(restClient.createObjectDataReader(Registration.class));

    }

    public GluonObservableObject<Registration> findRegistrationByID(RegistrationID regID)
    {
        // find the Registration by its identifier

        RestClient restClient = RestClient.create()
                .method("GET")
                .host("http://"+host+":8080")
                .path("/api/v1/REGISTRATION/{" + regID.getStudentDNI() + "}/{" + regID.getIdCourse() + "}");


        return DataProvider.retrieveObject(restClient.createObjectDataReader(Registration.class));
    }

}