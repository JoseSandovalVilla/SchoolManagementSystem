import java.util.ArrayList;
import java.util.HashMap;

public class GestorAcademico implements ServiciosAcademicosI {
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Curso> cursos;
    private HashMap<Integer, ArrayList<Estudiante>> inscripciones;

    public GestorAcademico() {
        this.estudiantes = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.inscripciones = new HashMap<>();
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante) throws EstudianteYaInscritoException {
        for (Estudiante e : estudiantes) {
            if (e.getId() == estudiante.getId()) {
                throw new EstudianteYaInscritoException("El estudiante ya está matriculado.");
            }
        }
        estudiantes.add(estudiante);
    }

    @Override
    public void agregarCurso(Curso curso) {
        for (Curso c : cursos) {
            if (c.getId() == curso.getId()) {
                return; // Curso ya existe, no se añade
            }
        }
        cursos.add(curso);
        inscripciones.put(curso.getId(), new ArrayList<>());
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        if (!inscripciones.containsKey(idCurso)) {
            throw new IllegalArgumentException("El curso con ID " + idCurso + " no existe.");
        }
        ArrayList<Estudiante> inscritos = inscripciones.get(idCurso);
        if (inscritos.contains(estudiante)) {
            throw new EstudianteYaInscritoException("El estudiante ya está inscrito en el curso.");
        }
        inscritos.add(estudiante);
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        if (!inscripciones.containsKey(idCurso)) {
            throw new IllegalArgumentException("El curso con ID " + idCurso + " no existe.");
        }
        ArrayList<Estudiante> inscritos = inscripciones.get(idCurso);
        Estudiante estudianteAEliminar = null;
        for (Estudiante e : inscritos) {
            if (e.getId() == idEstudiante) {
                estudianteAEliminar = e;
                break;
            }
        }
        if (estudianteAEliminar == null) {
            throw new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito en el curso.");
        }
        inscritos.remove(estudianteAEliminar);
    }
}
