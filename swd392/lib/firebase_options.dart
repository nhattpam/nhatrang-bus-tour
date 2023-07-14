// File generated by FlutterFire CLI.
// ignore_for_file: lines_longer_than_80_chars, avoid_classes_with_only_static_members
import 'package:firebase_core/firebase_core.dart' show FirebaseOptions;
import 'package:flutter/foundation.dart'
    show defaultTargetPlatform, kIsWeb, TargetPlatform;

/// Default [FirebaseOptions] for use with your Firebase apps.
///
/// Example:
/// ```dart
/// import 'firebase_options.dart';
/// // ...
/// await Firebase.initializeApp(
///   options: DefaultFirebaseOptions.currentPlatform,
/// );
/// ```
class DefaultFirebaseOptions {
  static FirebaseOptions get currentPlatform {
    if (kIsWeb) {
      return web;
    }
    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
        return android;
      case TargetPlatform.iOS:
        return ios;
      case TargetPlatform.macOS:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for macos - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      case TargetPlatform.windows:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for windows - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      case TargetPlatform.linux:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for linux - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      default:
        throw UnsupportedError(
          'DefaultFirebaseOptions are not supported for this platform.',
        );
    }
  }

  static const FirebaseOptions web = FirebaseOptions(
    apiKey: 'AIzaSyBQusS8Xw4hQxZi9eED-Uj3Cd6FsLf-TPM',
    appId: '1:577116573557:web:28892f883f1ff7f0a24d3c',
    messagingSenderId: '577116573557',
    projectId: 'login-gg-2023',
    authDomain: 'login-gg-2023.firebaseapp.com',
    storageBucket: 'login-gg-2023.appspot.com',
  );

  static const FirebaseOptions android = FirebaseOptions(
    apiKey: 'AIzaSyAUsaUMA6whjPJqEeJjzlZtP7zoLNkQEqI',
    appId: '1:577116573557:android:a6d59ed66ce337fca24d3c',
    messagingSenderId: '577116573557',
    projectId: 'login-gg-2023',
    storageBucket: 'login-gg-2023.appspot.com',
  );

  static const FirebaseOptions ios = FirebaseOptions(
    apiKey: 'AIzaSyBBXSznggekqWlOeGqohb_djwviKJLXsG0',
    appId: '1:577116573557:ios:9e4cb48526f0b5a0a24d3c',
    messagingSenderId: '577116573557',
    projectId: 'login-gg-2023',
    storageBucket: 'login-gg-2023.appspot.com',
    androidClientId: '577116573557-dqcih3javs3gb2fd81ik9mgsi7rhmkql.apps.googleusercontent.com',
    iosClientId: '577116573557-kphqg2ii04qmmmt1gri880pvgr8sbc4s.apps.googleusercontent.com',
    iosBundleId: 'com.bus.swd392',
  );
}
