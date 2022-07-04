import 'dart:ffi';

import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  static const TextStyle optionStyle =
      TextStyle(fontSize: 30, fontWeight: FontWeight.bold);
  static final List<Widget> _widgetOptions = <Widget>[
    const Text(
        'Index 0: Profil'
    ),
    GridView.count(
      crossAxisCount: 1,
      childAspectRatio: 2.2,
      mainAxisSpacing: 10.0,
      children: [
        Container(
          margin: const EdgeInsets.only(
            left: 10,
            right: 10
          ),
          height: 30,
          child: ElevatedButton.icon(
            onPressed: () {},
            label: const Text('REZEPTE'),
            icon: Icon(Icons.add_circle, size: 70,),
            style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.lightGreen[100],
                    shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10)
                ),
            ),
          ),
        ),
        Container(
          height: 30,
          margin: const EdgeInsets.only(
              left: 10,
              right: 10
          ),
          child: ElevatedButton.icon(
            onPressed: () {},
            label: const Text('PLANEN'),
            icon: Icon(Icons.note_alt_outlined, size: 70,),
            style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.lightGreen[300],
                    shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10)
                )
            ),
          ),
        ),
        Container(
          height: 20,
          margin: const EdgeInsets.only(
              left: 10,
              right: 10
          ),
          child: ElevatedButton.icon(
            onPressed: () {},
            label: const Text('EINKAUFEN'),
            icon: Icon(Icons.shopping_cart_outlined, size: 70,),
            style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.lightGreen,
                    shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10)
                )
            ),
          ),
        )
      ],
    ),
    const Text('Index 2: Statistik')
  ];

  void _incrementCounter(int index) {
    setState(() {
      _counter = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: _widgetOptions.elementAt(_counter),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem> [
          BottomNavigationBarItem(
              icon: Icon(Icons.person),
              label: 'Profile'
          ),
          BottomNavigationBarItem(
              icon: Icon(Icons.home),
              label: 'Home'),
          BottomNavigationBarItem(
              icon: Icon(Icons.auto_graph),
              label: 'Statistik'
          ),
        ],
        currentIndex: _counter,
        selectedItemColor: Colors.lightGreen,
        onTap: _incrementCounter,
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
