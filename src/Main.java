public class Main {
    public static void main(String[] args) {

        Estudiante estudiante1 = new Estudiante(1, "Juan", "Pérez", "2000-01-01", Estudiante.Estado.MATRICULADO);
        Estudiante estudiante2 = new Estudiante(2, "María", "Gómez", "1999-05-23", Estudiante.Estado.MATRICULADO);


        Curso curso1 = new Curso(1, "Matemáticas", "Curso de álgebra y cálculo", 3, "1.0");
        Curso curso2 = new Curso(2, "Historia", "Historia Universal", 2, "1.0");


        GestorAcademico gestor = new GestorAcademico();

        try {

            gestor.matricularEstudiante(estudiante1);
            gestor.matricularEstudiante(estudiante2);


            gestor.agregarCurso(curso1);
            gestor.agregarCurso(curso2);


            gestor.inscribirEstudianteCurso(estudiante1, curso1.getId());
            gestor.inscribirEstudianteCurso(estudiante2, curso2.getId());


            gestor.desinscribirEstudianteCurso(estudiante1.getId(), curso1.getId());


            gestor.inscribirEstudianteCurso(estudiante1, curso1.getId());
        } catch (EstudianteYaInscritoException | EstudianteNoInscritoEnCursoException e) {
            System.out.println(e.getMessage());
        }
    }
}
