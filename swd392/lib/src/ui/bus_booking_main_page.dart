import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:swd392/src/ui/bus_booking_home_screen.dart';
import 'package:swd392/src/ui/profile_page.dart';

import '../../api/bus_list.dart';
import '../../api/my_ticket_screen.dart';
import '../../api/notice_screen.dart';
import 'notification_screen.dart';

final busTicketAppMenuIndex = StateProvider<int>((ref) => 0);
class BusBookingMainPage extends StatefulWidget {
  const BusBookingMainPage({super.key});

  @override
  State<BusBookingMainPage> createState() => _BusBookingMainPageState();
}


class _BusBookingMainPageState extends State<BusBookingMainPage> {
  @override
  Widget build(BuildContext context) {
    return Consumer(
        builder: (context,ref, _) {
          final index = ref.watch(busTicketAppMenuIndex);
          return Scaffold(
            body: SafeArea(
              child: IndexedStack(
                index: index,
                children: [
                  const BusBookingHomeScreen(),
                  const MyTicketScreen(),
                  const NotificationScreen(),
                  const ProfilePage(),
                ],
              ),
            ),
            bottomNavigationBar: SizedBox(
              height: 60,
              child: BottomNavigationBar(

                elevation: 4 ,

                selectedItemColor: Colors.yellow,
                unselectedItemColor: Colors.white ,
                currentIndex: index,
                onTap: (idx)=>ref.read(busTicketAppMenuIndex.notifier).state = idx,
                items: const [
                  BottomNavigationBarItem(
                    icon: Icon(
                      Icons.home_filled,
                    ),
                    label: "HOME",
                    backgroundColor: Colors.red,
                  ),
                  BottomNavigationBarItem(
                    icon: Icon(
                      Icons.confirmation_num_rounded,
                    ),
                    label: "MY TICKETS",
                    backgroundColor: Colors.red,
                  ),
                  BottomNavigationBarItem(
                    icon: Icon(
                      Icons.notifications_active_rounded,
                    ),
                    label: "NOTICE",
                    backgroundColor: Colors.red,
                  ),
                  BottomNavigationBarItem(
                    icon: Icon(
                      Icons.person,
                    ),
                    label: "ACCOUNT",
                    backgroundColor: Colors.red,
                  ),
                ],
              ),
            ),
          );
        }
    );
  }
}
