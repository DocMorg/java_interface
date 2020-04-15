set MODULE_PATH=%userprofile%/.m2/repository/org/openjfx/javafx-base/11.0.2/javafx-base-11.0.2-win.jar;^
%userprofile%/.m2/repository/org/openjfx/javafx-controls/11.0.2/javafx-controls-11.0.2-win.jar;^
%userprofile%/.m2/repository/org/openjfx/javafx-graphics/11.0.2/javafx-graphics-11.0.2-win.jar;

java --module-path "target/classes;%MODULE_PATH%" -m com.company/com.company.TodoListOOP