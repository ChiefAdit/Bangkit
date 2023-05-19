import 'package:flutter/material.dart';
import 'login.dart';

void main() {
  runApp(Hallocing());
}

class Hallocing extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'HalloCing',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      debugShowCheckedModeBanner: false,
      home: login(),
    );
  }
}
