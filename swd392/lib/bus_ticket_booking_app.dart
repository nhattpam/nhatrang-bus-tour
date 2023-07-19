import 'package:flutter/material.dart';
import 'package:swd392/src/ui/bus_booking_detail_page.dart';
import 'package:swd392/src/ui/bus_booking_home_screen.dart';
import 'package:swd392/src/ui/bus_booking_main_page.dart';
import 'package:go_router/go_router.dart';
import 'package:swd392/src/ui/bus_booking_select_page.dart';
import 'package:swd392/src/ui/login_page.dart';
import 'package:swd392/src/ui/opening_page.dart';
import 'package:swd392/src/ui/register_page.dart';

import 'api/my_ticket_screen.dart';

class BusTicketBookingApp extends StatelessWidget {
  BusTicketBookingApp({super.key});
  final appRoute = GoRouter(
    routes: [
      GoRoute(
        path: "/",
        builder: (context,state)=>  const OpeningPage(), //login page
        routes: [
          ShellRoute(
            routes: [
              GoRoute(
                path: "detail",
                builder: (context, state)=> const BusBookingDetailPage(),
              ),
              GoRoute(
                path: "seat",
                builder: (context, state)=> const BusBookingSelectPage(),
              ),
              GoRoute(
                path: "main",
                builder: (context, state)=> const BusBookingMainPage(),
              ),
              GoRoute(
                path: "login",
                builder: (context, state)=> const  LoginPage(),
              ),
              GoRoute(
                path: "opening",
                builder: (context, state)=> const  OpeningPage(),
              ),
              GoRoute(
                path: "register",
                builder: (context, state)=> const  RegisterPage(),
              ),
              GoRoute(
                path: "ticket",
                builder: (context, state)=> const  MyTicketScreen(),
              ),
            ],
          ),
        ],
      ),
    ],
  );

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      routerConfig: appRoute,
    );
  }
}

