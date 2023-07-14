import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

class OpeningPage extends StatefulWidget {
  const OpeningPage({super.key});

  @override
  State<OpeningPage> createState() => _OpeningPageState();
}

class _OpeningPageState extends State<OpeningPage> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Container(
          decoration: BoxDecoration(
            color: Colors.white, // You can change the background color here
          ),
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                // Big Picture at the Center
                Container(
                  width: 350, // Adjust the width as per your requirements
                  height: 350, // Adjust the height as per your requirements
                  decoration: BoxDecoration(
                    image: DecorationImage(
                      image: AssetImage('assets/icon/signin.jpg'), // Replace 'your_image.png' with the actual image path
                      fit: BoxFit.contain,
                    ),
                  ),
                ),
                SizedBox(height: 20), // Adjust the spacing between the image and the text

                // Text "Start by creating an account."
                Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 20),
                  child: Text(
                    'Start by creating an account.',
                    style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold
                    ), // Adjust the font size as per your requirements
                    textAlign: TextAlign.center,
                  ),
                ),
                SizedBox(height: 20), // Adjust the spacing between the text and buttons

                // CREATE AN ACCOUNT Button
                ElevatedButton(
                  onPressed: () {
                    context.push('/register');
                  },
                  style: ElevatedButton.styleFrom(
                    foregroundColor: Colors.white,
                    backgroundColor: Colors.red, // Change the button color here
                  ),
                  child: Text('CREATE AN ACCOUNT'),
                ),

                SizedBox(height: 10), // Adjust the spacing between the buttons

                // SIGN IN Button
                Container(
                  width: 180,
                  child: ElevatedButton(
                    onPressed: () {
                      context.push('/login');
                    },
                    style: ElevatedButton.styleFrom(
                      foregroundColor: Colors.black,
                      backgroundColor: Colors.grey, // Use the same text color as the "CREATE AN ACCOUNT" button
                    ),
                    child: Text('SIGN IN'),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}