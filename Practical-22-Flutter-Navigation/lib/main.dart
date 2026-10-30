import 'package:flutter/material.dart';
void main() {
  runApp(const MyApp());
}
class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: HomeScreen(),
    );
  }
}
class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Home Screen'),
        backgroundColor: Colors.blue,
      ),
      body: Center(
        child: ElevatedButton(
          child: const Text('Go to Next Screen'),
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => const SecondScreen(),
              ),
            );
          },
        ),
      ),
    );
  }}
class SecondScreen extends StatelessWidget {
  const SecondScreen({super.key});
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Next Screen'),
        backgroundColor: Colors.red,
      ),
      body: Center(
        child: ElevatedButton(
          child: const Text('Go Back'),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
      ),);}}
