import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:go_router/go_router.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:rflutter_alert/rflutter_alert.dart';
import 'package:swd392/src/ui/profile_page.dart';
import 'package:firebase_messaging/firebase_messaging.dart';

import 'bus_booking_home_screen.dart';
import 'bus_booking_main_page.dart';
import 'bus_booking_select_page.dart';
import 'google_login_check.dart';
import 'login_check.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();

}

class _LoginPageState extends State<LoginPage> {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final GoogleSignIn _googleSignIn = GoogleSignIn();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  bool _isLoading = false;
  String _errorMessage = '';

  @override
  void initState(){
    super.initState();
    init();
  }

  init() async{
    String deviceToken = await getDeviceToken();
    print("##### PRINT DEVICE TOKEN TO USE FOR PUSH NOTIFICATION #####");
    print(deviceToken);
    print("###########################################################");

    // listen for user to click on notification
    FirebaseMessaging.onMessageOpenedApp.listen((RemoteMessage remoteMessage) {
      String? title = remoteMessage.notification!.title;
      String? description = remoteMessage.notification!.body;

      // alert dialog
      Alert(
        context: context,
        type: AlertType.success,
        title: title,
        desc: description,
        buttons: [
          DialogButton(
            child: Text(
              "CONFIRM",
              style: TextStyle(color: Colors.white, fontSize: 20),
            ),
            onPressed: () => Navigator.pop(context),
            width: 120,
          )
        ],
      ).show();

    });

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.red,
        title: Text('Sign in to your account'),
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
                controller: _emailController,
                decoration: InputDecoration(
                  labelText: 'Email',
                  prefixIcon: Icon(Icons.email),
                ),
              ),
              SizedBox(height: 16.0),
              TextFormField(
                controller: _passwordController,
                decoration: InputDecoration(
                  labelText: 'Password',
                  prefixIcon: Icon(Icons.lock),
                ),
                obscureText: true,
              ),
              SizedBox(height: 8.0),
              Align(
                alignment: Alignment.centerRight,
                child: TextButton(
                  onPressed: () {
                    // Add functionality for password recovery
                  },
                  child: Text('Forgot your password?'),
                ),
              ),
              SizedBox(height: 10),
              ElevatedButton(
                onPressed: _isLoading ? null : _login,
                child: _isLoading ? CircularProgressIndicator() : Text('SIGN IN'),
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.red,
                ),
              ),
              SizedBox(height: 16.0),
              Text(
                _errorMessage, // Display the error message here
                style: TextStyle(
                  color: Colors.red,
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
                    onPressed: _isLoading ? null : _loginWithGoogle,
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
                    context.push('/register');
                  },
                  child: Text('Not a member? Sign up'),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  } //ui ux

  // NORMAL LOGIN
  Future<void> _login() async {
    final String email = _emailController.text.trim();
    final String password = _passwordController.text.trim();

    setState(() {
      _isLoading = true;
      _errorMessage = '';
    });

    try {
      UserCredential userCredential = await _auth.signInWithEmailAndPassword(
        email: email,
        password: password,
      );

      // Login successful, navigate to the next screen or perform any other logic

      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => BusBookingMainPage()),
      );
      setState(() {
        _isLoading = false;
      });
    } on FirebaseAuthException catch (e) {
      setState(() {
        if (e.code == 'user-not-found') {
          _errorMessage = 'Email or password is wrong. Please try again.';
        } else if (e.code == 'wrong-password') {
          _errorMessage = 'Email or password is wrong. Please try again.';
        } else {
          _errorMessage = 'Error: ${e.message}';
        }
      });

      setState(() {
        _isLoading = false;
      });
    } catch (e) {
      setState(() {
        _errorMessage = 'Error: $e';
        _isLoading = false;
      });
    }
  }

  // GOOGLE LOGIN
  Future<void> _loginWithGoogle() async {
    setState(() {
      _isLoading = true;
      _errorMessage = '';
    });

    try {
      final GoogleSignInAccount? googleUser = await _googleSignIn.signIn();
      final GoogleSignInAuthentication googleAuth =
      await googleUser!.authentication;

      final OAuthCredential credential = GoogleAuthProvider.credential(
        accessToken: googleAuth.accessToken,
        idToken: googleAuth.idToken,
      );

      final UserCredential userCredential =
      await FirebaseAuth.instance.signInWithCredential(credential);

      // Login successful, navigate to the next screen or perform any other logic

      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => BusBookingMainPage()),
      );
      setState(() {
        _isLoading = false;
      });
    } on FirebaseAuthException catch (e) {
      setState(() {
        _errorMessage = 'Google login failed. Please try again.';
      });

      setState(() {
        _isLoading = false;
      });
    } catch (e) {
      setState(() {
        _errorMessage = 'Error: $e';
        _isLoading = false;
      });
    }
  }


  //get device token
  Future getDeviceToken() async {
    FirebaseMessaging _firebaseMessage = FirebaseMessaging.instance;
    String? deviceToken = await _firebaseMessage.getToken();
    return(deviceToken == null) ? "" :deviceToken;
    }
}
