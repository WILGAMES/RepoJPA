### findByUsername
Encuentra todos los estudiantes cuyo username sea igual a un string dado.

```java
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByUsername(String username);
}
```

**Ejemplo de uso:**
```java
@Autowired
private StudentRepository repo;

public List<Student> findStudentsByUsername(String username) {
    return repo.findByUsername(username);
}
```

---

### findByEmailIgnoreCase
Busca estudiantes por email, ignorando mayúsculas/minúsculas.

```java
List<Student> findByEmailIgnoreCase(String email);
```

**Uso:**
```java
List<Student> s = repo.findByEmailIgnoreCase("ALICE@EXAMPLE.COM");
// Encuentra registros aunque el email esté en distinta capitalización.
```

---

### findByFirstNameAndLastName
Busca estudiantes por `firstName` y `lastName`.

```java
List<Student> findByFirstNameAndLastName(String firstName, String lastName);
```

**Uso:**
```java
repo.findByFirstNameAndLastName("Alice", "Lopez");
```

---

## 📊 Operadores de Comparación

### findByAgeGreaterThan
Encuentra estudiantes con age mayor a un valor.

```java
List<Student> findByAgeGreaterThan(Integer age);
```

**Uso:**
```java
repo.findByAgeGreaterThan(21);
```

---

### findByAgeGreaterThanEqual
Encuentra estudiantes con age mayor o igual a un valor.

```java
List<Student> findByAgeGreaterThanEqual(Integer age);
```

---

### findByGpaBetween
Encuentra estudiantes con gpa dentro de un rango (inclusive).

```java
List<Student> findByGpaBetween(Double min, Double max);
```

**Uso:**
```java
repo.findByGpaBetween(3.0, 4.0);
```

---

### findByActiveTrue / findByActiveFalse
Encuentra estudiantes activos o inactivos (campo boolean `active`).

```java
List<Student> findByActiveTrue();
List<Student> findByActiveFalse();
```

**Uso:**
```java
repo.findByActiveTrue();
```

---

### findByRegistrationDateAfter
Encuentra estudiantes que se registraron después de una fecha dada.

```java
List<Student> findByRegistrationDateAfter(Timestamp date);
```

**Uso:**
```java
repo.findByRegistrationDateAfter(Timestamp.valueOf(LocalDateTime.of(2024, 1, 1, 0, 0)));
```

---

### findByRegistrationDateBetween
Encuentra estudiantes registrados entre dos fechas.

```java
List<Student> findByRegistrationDateBetween(Timestamp from, Timestamp to);
```

**Uso:**
```java
repo.findByRegistrationDateBetween(
    Timestamp.valueOf(LocalDateTime.of(2024,1,1,0,0)), 
    Timestamp.valueOf(LocalDateTime.of(2024,12,31,23,59))
);
```

---

## 🔗 Búsqueda por Relaciones

### findByCoursesCode
Encuentra estudiantes que estén inscritos en un curso con un código dado (join implícito con collection `courses`).

```java
List<Student> findByCoursesCode(String code);
```

**Uso:**
```java
repo.findByCoursesCode("CS101");
```

---

### findByCoursesTitleContainingIgnoreCase
Busca estudiantes que tengan cursos cuyo título contenga una subcadena (case-insensitive).

```java
List<Student> findByCoursesTitleContainingIgnoreCase(String titlePart);
```

**Uso:**
```java
repo.findByCoursesTitleContainingIgnoreCase("data");
```

---

### findDistinctByCoursesCode
Encuentra estudiantes distintos (sin duplicados) inscritos en el curso code.

```java
List<Student> findDistinctByCoursesCode(String code);
```

**Uso:**
```java
repo.findDistinctByCoursesCode("CS101");
```

---

### findByAdvisorLastName
Encuentra estudiantes cuyo advisor (ManyToOne Instructor) tiene un apellido dado.

```java
List<Student> findByAdvisorLastName(String lastName);
```

**Uso:**
```java
repo.findByAdvisorLastName("Gonzalez");
```

---

### findByAdvisorId
Encuentra estudiantes por el id del advisor.

```java
List<Student> findByAdvisorId(Long instructorId);
```

**Uso:**
```java
repo.findByAdvisorId(42L);
```

---

### findByAdvisorIsNull
Encuentra estudiantes que no tienen advisor asignado.

```java
List<Student> findByAdvisorIsNull();
```

**Uso:**
```java
repo.findByAdvisorIsNull();
```

---

## ✅ Operaciones de Existencia y Conteo

### existsByEmail
Comprueba si existe un estudiante con un email dado.

```java
boolean existsByEmail(String email);
```

**Uso:**
```java
boolean exists = repo.existsByEmail("alice@example.com");
```

---

### existsByUsername
Comprueba existencia por username.

```java
boolean existsByUsername(String username);
```

**Uso:**
```java
boolean e = repo.existsByUsername("kevin");
```

---

### countByActiveTrue
Cuenta cuántos estudiantes están activos.

```java
long countByActiveTrue();
```

**Uso:**
```java
long activeStudents = repo.countByActiveTrue();
```

---

## 🗑️ Operaciones de Eliminación

### deleteByUsername
Borra estudiantes por username (método reservado: delete...).

```java
void deleteByUsername(String username);
```

**Uso:**
```java
repo.deleteByUsername("old_user");
```

---

### deleteAllByActiveFalse
Borra todos los estudiantes inactivos (método reservado deleteAllBy...).

```java
void deleteAllByActiveFalse();
```

**Uso:**
```java
repo.deleteAllByActiveFalse();
```

---

## 📄 Paginación y Ordenamiento

### findTop5ByOrderByGpaDesc
Devuelve los 5 estudiantes con mayor GPA (Top / OrderBy).

```java
List<Student> findTop5ByOrderByGpaDesc();
```

**Uso:**
```java
List<Student> top5 = repo.findTop5ByOrderByGpaDesc();
```

---

### findFirstByOrderByRegistrationDateAsc
Encuentra el primer estudiante (el más antiguo) por fecha de registro.

```java
Optional<Student> findFirstByOrderByRegistrationDateAsc();
```

**Uso:**
```java
Optional<Student> first = repo.findFirstByOrderByRegistrationDateAsc();
```

---

### findTopByOrderByGpaAsc
Devuelve el estudiante con menor GPA.

```java
Optional<Student> findTopByOrderByGpaAsc();
```

**Uso:**
```java
Optional<Student> worst = repo.findTopByOrderByGpaAsc();
```

---

### findByActiveTrue (Paginado)
Paginación: devuelve estudiantes activos con Pageable.

```java
Page<Student> findByActiveTrue(Pageable pageable);
```

**Uso:**
```java
Page<Student> page = repo.findByActiveTrue(
    PageRequest.of(0, 20, Sort.by("gpa").descending())
);
```

---

### findByFirstNameOrderByLastNameAsc
Ordena: devuelve estudiantes con un firstName dado ordenados por lastName ascendente.

```java
List<Student> findByFirstNameOrderByLastNameAsc(String firstName);
```

---

### findByLastNameOrderByFirstNameDesc
Ordena por firstName descendente.

```java
List<Student> findByLastNameOrderByFirstNameDesc(String lastName);
```

---

### findByCoursesCodeOrderByCreditsDesc
Busca estudiantes por código de curso y ordena por créditos (desc).

```java
List<Student> findByCoursesCodeOrderByCoursesCreditsDesc(String courseCode);
```

**Uso:**
```java
repo.findByCoursesCodeOrderByCoursesCreditsDesc("CS101");
```

---

## 🌊 Búsqueda con Streams

### streamByActiveTrue
Devuelve un Stream de estudiantes activos (útil para procesar grandes resultados).

```java
Stream<Student> streamByActiveTrue();
```

**Uso** (recordar cerrar el stream si es necesario):
```java
try (Stream<Student> s = repo.streamByActiveTrue()) {
    s.forEach(...);
}
```

---

## 🔀 Operadores Lógicos y Condiciones Especiales

### findByFirstNameStartingWith
Busca estudiantes cuyo firstName empieza con un prefijo.

```java
List<Student> findByFirstNameStartingWith(String prefix);
```

**Uso:**
```java
repo.findByFirstNameStartingWith("Al");
```

---

### findByLastNameEndingWith
Busca estudiantes cuyo lastName termina con un sufijo.

```java
List<Student> findByLastNameEndingWith(String suffix);
```

**Uso:**
```java
repo.findByLastNameEndingWith("ez");
```

---

### findByFirstNameContaining
Busca estudiantes cuyo firstName contiene una subcadena.

```java
List<Student> findByFirstNameContaining(String fragment);
```

**Uso:**
```java
repo.findByFirstNameContaining("li");
```

---

### findByEmailLike
Busca estudiantes con email usando LIKE (usa % para comodines).

```java
List<Student> findByEmailLike(String pattern);
```

**Uso:**
```java
repo.findByEmailLike("%example.com");
```

---

### findByEmailEndingWith
Busca estudiantes cuyo email termina con una cadena dada.

```java
List<Student> findByEmailEndingWith(String suffix);
// Ej: findByEmailEndingWith("@gmail.com")
```

---

### findByGpaIsNull / findByGpaIsNotNull
Encuentra estudiantes cuyo gpa es NULL o no es NULL.

```java
List<Student> findByGpaIsNull();
List<Student> findByGpaIsNotNull();
```

---

### findByUsernameIn / findByUsernameNotIn
Encuentra estudiantes cuyo username está o NO está en una colección dada (IN / NOT IN).

```java
List<Student> findByUsernameIn(Collection<String> usernames);
List<Student> findByUsernameNotIn(Collection<String> usernames);
```

**Uso:**
```java
repo.findByUsernameIn(List.of("a","b","c"));
```

---

### findByUsernameNot
Encuentra estudiantes cuyo username NO sea el dado (NOT).

```java
List<Student> findByUsernameNot(String username);
```

---

### findByFirstNameNot
Encuentra estudiantes cuyo firstName no sea el dado.

```java
List<Student> findByFirstNameNot(String name);
```

---

### findByFirstNameOrLastName
Busca estudiantes cuyo firstName O lastName coincida con los parámetros.

```java
List<Student> findByFirstNameOrLastName(String first, String last);
```

**Uso:**
```java
repo.findByFirstNameOrLastName("Carlos","Gomez");
```

---

### findByFirstNameNotContaining
Encuentra estudiantes cuyo firstName NO contenga una subcadena.

```java
List<Student> findByFirstNameNotContaining(String fragment);
```

**Uso:**
```java
repo.findByFirstNameNotContaining("test");
```

---

### findByFirstNameIgnoreCaseAndLastNameIgnoreCase
Busca comparando firstName y lastName ignorando mayúsculas/minúsculas.

```java
List<Student> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String first, String last);
```

---

### findDistinctByFirstNameAndLastName
Devuelve resultados distintos al filtrar por nombre y apellido.

```java
List<Student> findDistinctByFirstNameAndLastName(String first, String last);
```

---

### findByEmailContainingIgnoreCaseAndActiveTrue
Combinación compleja: email contiene X (ignoring case) y active = true.

```java
List<Student> findByEmailContainingIgnoreCaseAndActiveTrue(String fragment);
```

**Uso:**
```java
repo.findByEmailContainingIgnoreCaseAndActiveTrue("example");
```

---

## 📚 Métodos de CourseRepository

### findByDepartmentName
En CourseRepository: busca cursos por el nombre del department asociado.

```java
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByDepartmentName(String deptName);
}
```

**Uso:**
```java
courseRepo.findByDepartmentName("Computer Science");
```

---

### findByCreditsLessThan
Encuentra cursos con menos de N créditos.

```java
List<Course> findByCreditsLessThan(int credits);
```

**Uso:**
```java
courseRepo.findByCreditsLessThan(4);
```

---

### findByLevelIn
Encuentra cursos cuyo level está dentro de una colección de niveles.

```java
List<Course> findByLevelIn(Collection<Level> levels);
```

**Uso:**
```java
courseRepo.findByLevelIn(List.of(Level.BEGINNER, Level.INTERMEDIATE));
```

---

## 📝 Métodos de Enrollments (Inscripciones)

### findByEnrollmentsGradeGreaterThan
Encuentra estudiantes que tengan alguna inscripción (enrollments) con nota mayor a X.

```java
List<Student> findByEnrollmentsGradeGreaterThan(Double grade);
```

**Uso:**
```java
repo.findByEnrollmentsGradeGreaterThan(85.0);
```

---

### findByEnrollmentsStatus
Encuentra estudiantes según el estado de alguna de sus inscripciones.

```java
List<Student> findByEnrollmentsStatus(EnrollmentStatus status);
```

**Uso:**
```java
repo.findByEnrollmentsStatus(EnrollmentStatus.COMPLETED);
```

---

### findByEnrollmentsSemesterAndCourseCode
Encuentra estudiantes inscritos en un curso específico durante un semestre concreto.

```java
List<Student> findByEnrollmentsSemesterAndEnrollmentsCourseCode(Semester semester, String courseCode);
```

**Uso:**
```java
repo.findByEnrollmentsSemesterAndEnrollmentsCourseCode(Semester.FALL, "CS101");
```

---

### findByEnrollmentsCourseCodeAndEnrollmentsStatus
Encuentra estudiantes por código de curso y estado de la inscripción.

```java
List<Student> findByEnrollmentsCourseCodeAndEnrollmentsStatus(String courseCode, EnrollmentStatus status);
```

**Uso:**
```java
repo.findByEnrollmentsCourseCodeAndEnrollmentsStatus("CS101", EnrollmentStatus.ENROLLED);
```

---

## 🔧 Métodos Combinados Avanzados

### findByCoursesLevelAndCreditsGreaterThan
Encuentra estudiantes que tienen al menos un curso con un level y más de N créditos.

```java
List<Student> findByCoursesLevelAndCoursesCreditsGreaterThan(Level level, int credits);
```

**Uso:**
```java
repo.findByCoursesLevelAndCoursesCreditsGreaterThan(Level.ADVANCED, 3);
```

---

### findByCoursesStartDateBefore
Encuentra estudiantes que tienen cursos cuyo startDate es anterior a una fecha.

```java
List<Student> findByCoursesStartDateBefore(Timestamp date);
```

**Uso:**
```java
repo.findByCoursesStartDateBefore(Timestamp.valueOf(LocalDateTime.now()));
```

---

### findDistinctByCoursesDepartmentName
Devuelve estudiantes distintos que estén en cursos de un depto con nombre dado.

```java
List<Student> findDistinctByCoursesDepartmentName(String deptName);
```

**Uso:**
```java
repo.findDistinctByCoursesDepartmentName("Mathematics");
```

---

### findByRegistrationDateYear (Ejemplo con Between)
Busca estudiantes registrados en un año determinado usando Between en fechas.

```java
List<Student> findByRegistrationDateBetween(Timestamp startOfYear, Timestamp endOfYear);
```

**Ejemplo de llamada:**
```java
repo.findByRegistrationDateBetween(
    Timestamp.valueOf(LocalDateTime.of(2024,1,1,0,0)), 
    Timestamp.valueOf(LocalDateTime.of(2024,12,31,23,59))
);
```

---

## 📖 Convenciones de Nomenclatura

Spring Data JPA deriva automáticamente las consultas basándose en el nombre del método siguiendo estas convenciones:

| Palabra clave | Descripción | Ejemplo |
|--------------|-------------|---------|
| `findBy` | Búsqueda básica | `findByUsername` |
| `findDistinctBy` | Elimina duplicados | `findDistinctByCoursesCode` |
| `findTopNBy` | Limita a N resultados | `findTop5ByOrderByGpaDesc` |
| `findFirstBy` | Primer resultado | `findFirstByOrderByRegistrationDateAsc` |
| `existsBy` | Verifica existencia | `existsByEmail` |
| `countBy` | Cuenta resultados | `countByActiveTrue` |
| `deleteBy` / `deleteAllBy` | Elimina registros | `deleteByUsername` |
| `streamBy` | Retorna Stream | `streamByActiveTrue` |

### Operadores Soportados

| Operador | Palabra clave | Ejemplo |
|----------|--------------|---------|
| Igualdad | (implícito) | `findByUsername` |
| AND | `And` | `findByFirstNameAndLastName` |
| OR | `Or` | `findByFirstNameOrLastName` |
| NOT | `Not` / `IsNot` | `findByUsernameNot` |
| Mayor que | `GreaterThan` | `findByAgeGreaterThan` |
| Mayor o igual | `GreaterThanEqual` | `findByAgeGreaterThanEqual` |
| Menor que | `LessThan` | `findByCreditsLessThan` |
| Entre | `Between` | `findByGpaBetween` |
| Like | `Like` / `Containing` | `findByEmailLike` |
| Inicia con | `StartingWith` | `findByFirstNameStartingWith` |
| Termina con | `EndingWith` | `findByLastNameEndingWith` |
| Is Null | `IsNull` | `findByGpaIsNull` |
| Is Not Null | `IsNotNull` | `findByGpaIsNotNull` |
| IN | `In` | `findByUsernameIn` |
| NOT IN | `NotIn` | `findByUsernameNotIn` |
| IgnoreCase | `IgnoreCase` | `findByEmailIgnoreCase` |
| OrderBy | `OrderBy...Asc/Desc` | `findByActiveTrueOrderByGpaDesc` |

---
