import 'package:flutter/material.dart';
void main() {
  runApp(MyApp());
}
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: ListPage(),
    );
  }
}
class ListPage extends StatefulWidget {
  @override
  State<ListPage> createState() => _ListPageState();
}
class _ListPageState extends State<ListPage> {
  List<String> items = ["Apple", "Banana", "Mango"];

  void addItem() {
    setState(() {
      items.add("Item ${items.length + 1}");
    });
  }
  void removeItem() {
    if (items.isNotEmpty) {
      setState(() {
        items.removeLast();
      });
    }
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("List Operations"),
        backgroundColor: Colors.deepPurple,
      ),
      body: Padding(
        padding: EdgeInsets.all(20),
        child: Column(
          children: [
            Text(
              "Prince",
              style: TextStyle(
                fontSize: 28,
                fontWeight: FontWeight.bold,),),
            SizedBox(height: 15),
            Text(
              "Items in List: ${items.length}",
              style: TextStyle(fontSize: 20),
            ),
            SizedBox(height: 15),
            Expanded(
              child: ListView.builder(
                itemCount: items.length,
                itemBuilder: (context, index) {
                  return Card(
                    child: ListTile(
                      leading: Icon(
                        Icons.list,
                        color: Colors.deepPurple,
                      ),
                      title: Text(items[index]),),);},),),
            SizedBox(height: 10),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(
                  onPressed: addItem,
                  child: Text("Add Item"),),
                ElevatedButton(
                  onPressed: removeItem,
                  child: Text("Remove Item"),),],),],),),);}}
