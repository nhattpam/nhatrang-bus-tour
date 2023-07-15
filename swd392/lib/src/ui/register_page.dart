import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'login_page.dart';

class RegisterPage extends StatefulWidget {
  const RegisterPage({super.key});

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  Future<void> registerUser(String email, String password) async {
    try {
      UserCredential userCredential = await _auth.createUserWithEmailAndPassword(
        email: email,
        password: password,
      );
      User? user = userCredential.user;
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => LoginPage()),
      );
      print('User registered: ${user?.uid}');
    } catch (e) {
      // Registration failed, handle the error
      print('Registration failed: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.red,
        title: Text('Sign up your account'),
        leading: IconButton(
          icon: Icon(Icons.arrow_back_ios),
          onPressed: () {
            context.push('/opening');
          },
        ),
      ),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              TextFormField(
                controller: emailController,
                decoration: InputDecoration(
                  labelText: 'Email',
                  prefixIcon: Icon(Icons.email),
                ),
              ),
              SizedBox(height: 16.0),
              TextFormField(
                controller: passwordController,
                decoration: InputDecoration(
                  labelText: 'Password',
                  prefixIcon: Icon(Icons.lock),
                ),
                obscureText: true,
              ),
              SizedBox(height: 30.0),

              ElevatedButton(
                onPressed: () {
                  String email = emailController.text.trim();
                  String password = passwordController.text.trim();

                  if (email.isEmpty || password.isEmpty) {
                    // Show an error message or handle the empty fields
                    print('Email or password cannot be empty');
                    return;
                  }

                  registerUser(email, password);
                },
                child: Text('CREATE AN ACCOUNT'),
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.red,
                ),
              ),
              SizedBox(height: 16.0),
              Text(
                '-----------------------OR-----------------------',
                style: TextStyle(
                  fontSize: 16.0,
                  fontWeight: FontWeight.bold,
                ),
              ),
              SizedBox(height: 16.0),
              Column(
                children: [
                  ElevatedButton.icon(
                    onPressed: (){
                      //
                    },
                    icon: Image.asset(
                      'assets/icon/google.png',
                      height: 24.0,
                    ),
                    label: Text('Login with Google'),
                    style: ElevatedButton.styleFrom(
                      foregroundColor: Colors.black,
                      backgroundColor: Colors.white,
                    ),
                  ),
                  SizedBox(height: 8.0),
                  ElevatedButton.icon(
                    onPressed: (){
                      // login fb
                    },
                    icon: Image.asset(
                      'assets/icon/facebook.png',
                      height: 24.0,
                    ),
                    label: Text('Login with Facebook'),
                    style: ElevatedButton.styleFrom(
                      backgroundColor: Colors.blue,
                    ),
                  ),
                ],
              ),
              SizedBox(height: 8.0),
              Align(
                alignment: Alignment.centerRight,
                child: TextButton(
                  onPressed: () {
                    context.push('/login');
                  },
                  child: Text('Have an account? Sign in'),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
