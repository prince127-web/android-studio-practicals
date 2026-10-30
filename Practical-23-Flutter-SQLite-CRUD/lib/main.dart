import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart' as p;
void main() {
  runApp(const MyApp());
}
class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(
          seedColor: Colors.deepPurple,
        ),
      ),
      home: const StudentScreen(),
    );
  }
}
// Database Helper
class StudentDatabase {
  static Database? _database;
  static Future<Database> getDatabase() async {
    if (_database != null) {
      return _database!;
    }
    final dbPath = p.join(
      await getDatabasesPath(),
      'student.db',
    );
    _database = await openDatabase(
      dbPath,
      version: 1,
      onCreate: (db, version) async {
        await db.execute('''
          CREATE TABLE students(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT,
            marks INTEGER
          )
        ''');
      },
    );
    return _database!;
  }
  // CREATE
  static Future<int> insertStudent(
      String name, int marks) async {
    final db = await getDatabase();
    return db.insert('students', {
      'name': name,
      'marks': marks,
    });
  }
  // READ
  static Future<List<Map<String, Object?>>> getStudents() async {
    final db = await getDatabase();
    return db.query('students', orderBy: 'id ASC');
  }
  // UPDATE
  static Future<int> updateStudent(
      int id, String name, int marks) async {
    final db = await getDatabase();
    return db.update(
      'students',
      {
        'name': name,
        'marks': marks,
      },
      where: 'id = ?',
      whereArgs: [id],
    );
  }
  // DELETE
  static Future<int> deleteStudent(int id) async {
    final db = await getDatabase();
    return db.delete(
      'students',
      where: 'id = ?',
      whereArgs: [id],
    );
  }
}
// Student Screen
class StudentScreen extends StatefulWidget {
  const StudentScreen({super.key});
  @override
  State<StudentScreen> createState() => _StudentScreenState();
}
class _StudentScreenState extends State<StudentScreen> {
  final nameController = TextEditingController();
  final marksController = TextEditingController();
  List<Map<String, Object?>> students = [];
  int? selectedId;
  @override
  void initState() {
    super.initState();
    loadStudents();
  }
  // READ
  Future<void> loadStudents() async {
    final data = await StudentDatabase.getStudents();

    if (!mounted) return;
    setState(() {
      students = data;
    });
  }
  // CREATE and UPDATE
  Future<void> saveStudent() async {
    final name = nameController.text.trim();
    final marks = int.tryParse(marksController.text.trim());
    if (name.isEmpty ||
        marks == null ||
        marks < 0 ||
        marks > 100) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Enter name and marks from 0 to 100'),
        ),
      );
      return;
    }
    if (selectedId == null) {
      await StudentDatabase.insertStudent(name, marks);
    } else {
      await StudentDatabase.updateStudent(
        selectedId!,
        name,
        marks,
      );
    }
    if (!mounted) return;
    nameController.clear();
    marksController.clear();
    setState(() {
      selectedId = null;
    });
    await loadStudents();
  }
  // SELECT STUDENT FOR UPDATE
  void editStudent(Map<String, Object?> student) {
    setState(() {
      selectedId = student['id'] as int;
      nameController.text = student['name'].toString();
      marksController.text = student['marks'].toString();
    });
  }
  // DELETE
  Future<void> removeStudent(int id) async {
    await StudentDatabase.deleteStudent(id);
    if (!mounted) return;
    if (selectedId == id) {
      nameController.clear();
      marksController.clear();
      setState(() {
        selectedId = null;
      });
    }
    await loadStudents();
  }
  @override
  void dispose() {
    nameController.dispose();
    marksController.dispose();
    super.dispose();
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Student CRUD Operations'),
        backgroundColor: Colors.deepPurple,
        foregroundColor: Colors.white,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          children: [
            const Text(
              'Prince',
              style: TextStyle(
                fontSize: 28,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 20),
            TextField(
              controller: nameController,
              decoration: const InputDecoration(
                labelText: 'Student Name',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.person),
              ),
            ),
            const SizedBox(height: 12),
            TextField(
              controller: marksController,
              keyboardType: TextInputType.number,
              decoration: const InputDecoration(
                labelText: 'Marks (0-100)',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.grade),
              ),
            ),
            const SizedBox(height: 12),
            SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                onPressed: saveStudent,
                child: Text(
                  selectedId == null
                      ? 'Insert Student'
                      : 'Update Student',
                ),
              ),
            ),
            if (selectedId != null)
              TextButton(
                onPressed: () {
                  nameController.clear();
                  marksController.clear();
                  setState(() {
                    selectedId = null;
                  });
                },
                child: const Text('Cancel Update'),
              ),
            const Divider(),
            const Align(
              alignment: Alignment.centerLeft,
              child: Text(
                'Student Records',
                style: TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                ),),),
            const SizedBox(height: 8),
            Expanded(
              child: students.isEmpty
                  ? const Center(
                      child: Text('No student records found'),
                    )
                  : ListView.builder(
                      itemCount: students.length,
                      itemBuilder: (context, index) {
                        final student = students[index];
                        return Card(
                          child: ListTile(
                            leading: const CircleAvatar(
                              child: Icon(Icons.person),
                            ),
                            title: Text(
                              student['name'].toString(),),
                            subtitle: Text(
                              'ID: ${student['id']} | '
                              'Marks: ${student['marks']}',),
                            onTap: () => editStudent(student),
                            trailing: IconButton(
                              icon: const Icon(
                                Icons.delete,
                                color: Colors.red,
                              ),
                              onPressed: () => removeStudent(
                                student['id'] as int,
                              ),),),);},), ),],), ),);}}
