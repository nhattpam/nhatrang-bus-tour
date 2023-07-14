import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';

class GoogleLogin extends StatefulWidget {
  const GoogleLogin({Key? key}) : super(key: key);

  @override
  State<GoogleLogin> createState() => _GoogleLoginState();
}

class _GoogleLoginState extends State<GoogleLogin> {
  GoogleSignIn _googleSignIn = GoogleSignIn(
    scopes: [
      'email',
      'profile',
    ],
  );

  String _userName = '';
  String _userEmail = '';
  String? _userImage = '';

  Future<void> _loginWithGoogle() async {
    try {
      GoogleSignInAccount? googleUser = await _googleSignIn.signIn();
      GoogleSignInAuthentication googleAuth = await googleUser!.authentication;

      String? accessToken = googleAuth.accessToken;
      String? idToken = googleAuth.idToken;

      // Use the accessToken and idToken to authenticate with Firebase
      // ...

      setState(() {
        _userName = googleUser.displayName ?? '';
        _userEmail = googleUser.email ?? '';
        _userImage = googleUser.photoUrl;
      });
    } catch (error) {
      print('Google login failed: $error');
      // Handle login error
      // ...
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        ElevatedButton(
          onPressed: _loginWithGoogle,
          child: Text('Login with Google'),
        ),
        SizedBox(height: 16.0),
        CircleAvatar(
          radius: 50.0,
          backgroundImage: _userImage != null
              ? NetworkImage(_userImage!) // Add the null check with '!'
              : AssetImage('assets/icon/ip14.jpg') as ImageProvider<Object>,
        ),
        SizedBox(height: 16.0),
        Text(
          'Name: $_userName',
          style: TextStyle(fontSize: 16.0),
        ),
        SizedBox(height: 8.0),
        Text(
          'Email: $_userEmail',
          style: TextStyle(fontSize: 16.0),
        ),
      ],
    );
  }
}

