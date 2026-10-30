import 'package:flutter/material.dart';
void main() {
  runApp(MaterialApp(
    home: StudentFormScreen(),
    debugShowCheckedModeBanner: false,
  ));
}
class StudentFormScreen extends StatefulWidget {
  const StudentFormScreen({super.key});
  @override
  State<StudentFormScreen> createState() =>
      _StudentFormScreenState();
}
class _StudentFormScreenState extends State<StudentFormScreen> {
  final TextEditingController nameController =
  TextEditingController();
  bool isInterested = false;
  bool notifications = true;
  String selectedCourse = 'MCA';
  @override
  void dispose() {
    nameController.dispose();
    super.dispose();
  }
  void showStudentDetails() {
    String name = nameController.text.trim();
    if (name.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Please enter your name'),
        ),
      );
      return;
    }
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Text('Student Details'),
        content: Text(
          'Name: $name\n'
              'Course: $selectedCourse\n'
              'Interested: ${isInterested ? "Yes" : "No"}\n'
              'Notifications: ${notifications ? "On" : "Off"}',
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('OK'),
          ),
        ],
      ),
    );
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Student Registration'),
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text('Enter Student Name'),
            TextField(
              controller: nameController,
              decoration: const InputDecoration(
                hintText: 'Enter your name',
                border: OutlineInputBorder(),
              ),
            ),
            const SizedBox(height: 16),
            const Text('Select Course'),
            DropdownButton<String>(
              value: selectedCourse,
              isExpanded: true,
              items: ['MCA', 'MBA', 'BCA', 'BMS']
                  .map((course) {
                return DropdownMenuItem<String>(
                  value: course,
                  child: Text(course),
                );
              }).toList(),
              onChanged: (value) {
                setState(() {
                  selectedCourse = value!;
                });
              },
            ),
            CheckboxListTile(
              title: const Text('Interested in Programming'),
              value: isInterested,
              onChanged: (value) {
                setState(() {
                  isInterested = value!;
                });
              },
              controlAffinity: ListTileControlAffinity.leading,
              contentPadding: EdgeInsets.zero,
            ),
            SwitchListTile(
              title: const Text('Enable Notifications'),
              value: notifications,
              onChanged: (value) {
                setState(() {
                  notifications = value;
                });
              },
              contentPadding: EdgeInsets.zero,
            ),
           const SizedBox(height: 16),
            ElevatedButton(
              onPressed: showStudentDetails,
              child: const Text('Submit'),
            ),
            TextButton(
              onPressed: () {
                setState(() {
                  nameController.clear();
                  selectedCourse = 'MCA';
                  isInterested = false;
                  notifications = true;
                });
              },
              child: const Text('Reset'),
            ),
          ],
        ),
      ),
    );
  }
}
