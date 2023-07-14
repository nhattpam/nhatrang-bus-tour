import 'package:flutter/material.dart';

class NotificationScreen extends StatefulWidget {
  const NotificationScreen({super.key});

  @override
  State<NotificationScreen> createState() => _NotificationScreenState();
}

class _NotificationScreenState extends State<NotificationScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false, // Remove the back arrow
        titleSpacing: 0,
        title: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16), // Add horizontal padding
          child: Align(
            alignment: Alignment.centerRight, // Adjust the alignment
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                const Text(
                  'Notifications',
                  style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                TextButton(
                  onPressed: () {
                    // Perform clear all logic here
                  },
                  child: const Text(
                    'Clear All',
                    style: TextStyle(
                      fontSize: 14,
                      color: Colors.white,
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
        backgroundColor: Colors.red,
      ),
      body: ListView(
        padding: const EdgeInsets.all(10),
        children: [
          _buildNotificationTile(
            Icons.check_circle,
            'Ticket booked successfully',
            'Your ticket has been booked successfully.',
            '11:30 AM',
          ),
          const SizedBox(height: 10),
          _buildNotificationTile(
            Icons.timer,
            'Reminder: Upcoming trip',
            'Your upcoming trip is scheduled for tomorrow.',
            '8:00 PM',
          ),
        ],
      ),
    );
  }

  Widget _buildNotificationTile(
      IconData icon,
      String title,
      String content,
      String time,
      ) {
    return ListTile(
      leading: Icon(
        icon,
        color: Colors.green,
      ),
      title: Text(
        title,
        style: const TextStyle(
          fontSize: 16,
          fontWeight: FontWeight.bold,
        ),
      ),
      subtitle: Text(
        content,
        style: const TextStyle(fontSize: 14),
      ),
      trailing: Text(
        time,
        style: const TextStyle(fontSize: 12),
      ),
    );
  }
}
