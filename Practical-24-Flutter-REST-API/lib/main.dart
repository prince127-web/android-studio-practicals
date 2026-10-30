import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
void main() {
  runApp(const MyApp());
}
// Main application
class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: UserScreen(),
    );
  }
}
// Screen to display API data
class UserScreen extends StatefulWidget {
  const UserScreen({super.key});
  @override
  State<UserScreen> createState() => _UserScreenState();
}
class _UserScreenState extends State<UserScreen> {
  List users = [];
  bool isLoading = true;
  String errorMessage = '';
  @override
  void initState() {
    super.initState();
    fetchUsers();
  }
  // Fetch users from REST API
  Future<void> fetchUsers() async {
    try {
      final response = await http.get(
        Uri.parse('https://jsonplaceholder.typicode.com/users'),
      );

      if (response.statusCode == 200) {
        setState(() {
          users = jsonDecode(response.body);
          isLoading = false;
          errorMessage = '';
        });
      } else {
        throw Exception('Failed to load users');
      }
    } catch (e) {
      setState(() {
        isLoading = false;
        errorMessage = 'Unable to fetch data. Check internet.';
      });
    }
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('REST API - User Details'),
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh),
            onPressed: () {
              setState(() {
                isLoading = true;
                errorMessage = '';
              });
              fetchUsers();
            },
          ),
        ],
      ),
      body: isLoading
          ? const Center(
              child: CircularProgressIndicator(),
            )
          : errorMessage.isNotEmpty
              ? Center(
                  child: Text(errorMessage),
                )
              : ListView.builder(
                  itemCount: users.length,
                  itemBuilder: (context, index) {
                    final user = users[index];
                    return Card(
                      margin: const EdgeInsets.all(8),
                      child: ListTile(
                        leading: CircleAvatar(
                          child: Text('${user['id']}'),
                        ),
                        title: Text(user['name']),
                        subtitle: Text(user['email']),
                      ),
                    );
                  },
                ),
    );
  }
}
