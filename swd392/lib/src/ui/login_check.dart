import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_storage/firebase_storage.dart' as firebase_storage;

class LoginCheckPage extends StatefulWidget {
  @override
  State<LoginCheckPage> createState() => _LoginCheckPageState();
}

class _LoginCheckPageState extends State<LoginCheckPage> {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  User? _user;
  String? _userImageUrl;

  @override
  void initState() {
    super.initState();
    _user = _auth.currentUser;
    _getUserImageUrl();
  }

  Future<void> _getUserImageUrl() async {
    if (_user != null) {
      try {
        final ref = firebase_storage.FirebaseStorage.instance.ref().child('s23.jpg');
        _userImageUrl = await ref.getDownloadURL();
      } catch (e) {
        print('Error getting user image URL: $e');
        _userImageUrl = null;
      }
      if (mounted) {
        setState(() {});
      }
    }
  }

  Widget getProfileImage() {
    if (_userImageUrl != null) {
      return CircleAvatar(
        radius: 50.0,
        backgroundImage: NetworkImage(_userImageUrl!),
      );
    } else {
      return CircleAvatar(
        radius: 50.0,
        child: Icon(Icons.account_circle, size: 100),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Login Check'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            getProfileImage(),
            SizedBox(height: 16.0),
            Text(
              'Logged in user email:',
              style: TextStyle(fontSize: 18),
            ),
            SizedBox(height: 8),
            Text(
              _user?.email ?? 'No user logged in',
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
          ],
        ),
      ),
    );
  }
}
