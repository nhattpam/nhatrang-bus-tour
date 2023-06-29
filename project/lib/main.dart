import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'firebase_options.dart';
import 'package:google_sign_in/google_sign_in.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: LoginIn(),
    );
  }
}

class LoginIn extends StatefulWidget {
  LoginIn({Key? key}) : super(key: key);

  @override
  State<LoginIn> createState() => _LoginInState();
}

class _LoginInState extends State<LoginIn> {
  final GoogleSignIn _googleSignIn = GoogleSignIn();
  bool _isLoggedIn = false;
  GoogleSignInAccount? _userObj;

  @override
  void initState() {
    super.initState();
    _isLoggedIn = false; // Initialize the login state to false

    _googleSignIn.onCurrentUserChanged.listen((GoogleSignInAccount? account) {
      setState(() {
        _userObj = account;
        _isLoggedIn = _userObj != null;
      });
    });

    _googleSignIn.isSignedIn().then((isSignedIn) {
      setState(() {
        _isLoggedIn = isSignedIn;
      });
    });
  }

  Future<void> _handleSignIn() async {
    try {
      final account = await _googleSignIn.signIn();
      setState(() {
        _userObj = account;
        _isLoggedIn = true;
      });
    } catch (error) {
      print(error);
    }
  }


  Future<void> _handleSignOut() async {
    try {
      await _googleSignIn.signOut();
      setState(() {
        _userObj = null;
        _isLoggedIn = false;
      });
    } catch (error) {
      print(error);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Nha Trang Bus Tour'),
      ),
      body: Container(
        child: _isLoggedIn
            ? Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              _userObj != null && _userObj!.photoUrl != null && _userObj!.photoUrl!.isNotEmpty
                  ? CircleAvatar(
                backgroundImage: NetworkImage(_userObj!.photoUrl!),
                radius: 50,
              )
                  : Container(),
              const SizedBox(height: 20),
              Text(_userObj?.displayName ?? ''),
              const SizedBox(height: 20),
              Text(_userObj?.email ?? ''),
              const SizedBox(height: 20),
              MaterialButton(
                onPressed: _handleSignOut,
                height: 50,
                minWidth: 100,
                color: Colors.red,
                child: const Text(
                  'Logout',
                  style: TextStyle(color: Colors.white),
                ),
              )
            ],
          ),
        )
            : Center(
          child: MaterialButton(
            onPressed: _handleSignIn,
            height: 50,
            minWidth: 100,
            color: Colors.red,
            child: const Text(
              'Google Signin',
              style: TextStyle(color: Colors.white),
            ),
          ),
        ),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Home',
            backgroundColor: Colors.red,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.airplane_ticket),
            label: 'My Tickets',
            backgroundColor: Colors.red,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.notifications),
            label: 'Notifications',
            backgroundColor: Colors.red,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person),
            label: 'Profile',
            backgroundColor: Colors.red,
          ),
        ],
        currentIndex: 0,
        onTap: (index) {},
      ),
    );
  }
}


