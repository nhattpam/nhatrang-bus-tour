import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:swd392/model/station.dart';
import 'package:swd392/network/network_request_station.dart';
import 'package:google_fonts/google_fonts.dart';

class BusBookingHomeScreen extends StatefulWidget {
  const BusBookingHomeScreen({Key? key}) : super(key: key);

  @override
  _BusBookingHomeScreenState createState() => _BusBookingHomeScreenState();
}

class _BusBookingHomeScreenState extends State<BusBookingHomeScreen> {
  late String selectedFrom;
  late String selectedTo;
  late DateTime selectedDate;
  List<Station> stations = []; // List to hold the fetched stations

  @override
  void initState() {
    super.initState();
    selectedFrom = '';
    selectedTo = '';
    selectedDate = DateTime.now();

    fetchStations(); // Fetch the stations from the API
  }

  Future<void> fetchStations() async {
    try {
      List<Station> fetchedStations = await NetworkRequestStation.fetchStation();
      setState(() {
        stations = fetchedStations;
        if (stations.isNotEmpty) {
          selectedFrom = stations[0].stationName ?? '';
          selectedTo = stations[0].stationName ?? '';
        }
      });
    } catch (e) {
      print('Failed to fetch stations: $e');
    }
  }

  Future<void> _selectDate() async {
    final DateTime? pickedDate = await showDatePicker(
      context: context,
      initialDate: selectedDate,
      firstDate: DateTime(2022),
      lastDate: DateTime(2025),
    );

    if (pickedDate != null && pickedDate != selectedDate) {
      setState(() {
        selectedDate = pickedDate;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Container(
          color: Colors.red,
          child: Column(
            children: [
              Container(
                padding: EdgeInsets.symmetric(vertical: 10, horizontal: 10),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Container(
                      width: 48,
                      height: 48,
                      decoration: BoxDecoration(
                        color: Colors.red,
                        border: Border.all(
                          color: Colors.white,
                          width: 1,
                        ),
                        borderRadius: BorderRadius.circular(24),
                      ),
                      child: Icon(
                        Icons.directions_bus_filled,
                        color: Colors.white,
                        size: 40,
                      ),
                    ),
                    Spacer(),
                    SizedBox(width: 10),
                    IconButton(
                      icon: Icon(
                        Icons.menu,
                        color: Colors.white,
                        size: 42,
                      ),
                      onPressed: () {
                        // context.push("/detail");
                        // Add your menu button onPressed logic here
                      },
                    ),
                  ],
                ),
              ),

              // --Booking--
              Container(
                margin: EdgeInsets.symmetric(horizontal: 10, vertical: 10),
                child: Container(
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(10),
                  ),
                  height: 500,
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: [
                      Row(
                        children: [
                          Container(
                            width: 24,
                            height: 25,
                            decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(8),
                            ),

                            // --FROM--
                            child: Icon(
                              Icons.map,
                              color: Colors.red,
                              size: 24,
                            ),
                          ),
                          SizedBox(width: 8),
                          Text(
                            "FROM",
                            style: GoogleFonts.roboto(
                              color: Colors.black,
                              fontWeight: FontWeight.bold,
                              fontSize: 20,
                            ),
                          ),
                        ],
                      ),
                      SizedBox(height: 12),
                      Align(
                        alignment: Alignment.centerLeft,
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Row(
                              children: [
                                Expanded(
                                  child: DropdownButtonFormField<String>(
                                    value: selectedFrom,
                                    onChanged: (newValue) {
                                      setState(() {
                                        selectedFrom = newValue!;
                                      });
                                    },
                                    items: stations.map((Station station) {
                                      return DropdownMenuItem<String>(
                                        value: station.stationName,
                                        child: Text(
                                          station.stationName ?? '',
                                          style: TextStyle(
                                            color: Colors.black,
                                            fontSize: 15,
                                            fontFamily: "Inter",
                                            fontWeight: FontWeight.w500,
                                          ),
                                        ),
                                      );
                                    }).toList(),
                                  ),
                                ),
                              ],
                            ),
                            SizedBox(height: 4),
                          ],
                        ),
                      ),
                      SizedBox(height: 12),
                      Row(
                        children: [
                          Container(
                            width: 24,
                            height: 25,
                            decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(8),
                            ),

                            // --TO--
                            child: Icon(
                              Icons.location_on_outlined,
                              color: Colors.red,
                              size: 24,
                            ),
                          ),
                          SizedBox(width: 8),
                          Text(
                            "TO",
                            style: TextStyle(
                              fontFamily: 'Roboto',
                              color: Colors.black,
                              fontWeight: FontWeight.bold,
                              fontSize: 20,
                            ),
                          ),
                        ],
                      ),
                      SizedBox(height: 12),
                      Align(
                        alignment: Alignment.centerLeft,
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Row(
                              children: [
                                Expanded(
                                  child: DropdownButtonFormField<String>(
                                    value: selectedTo,
                                    onChanged: (newValue) {
                                      setState(() {
                                        selectedTo = newValue!;
                                      });
                                    },
                                    items: stations.map((Station station) {
                                      return DropdownMenuItem<String>(
                                        value: station.stationName,
                                        child: Text(
                                          station.stationName ?? '',
                                          style: TextStyle(
                                            color: Colors.black,
                                            fontSize: 15,
                                            fontFamily: "Inter",
                                            fontWeight: FontWeight.w500,
                                          ),
                                        ),
                                      );
                                    }).toList(),
                                    style: TextStyle(
                                      color: Colors.black,
                                      fontSize: 12,
                                      fontFamily: "Inter",
                                      fontWeight: FontWeight.w300,
                                    ),
                                  ),
                                ),
                              ],
                            ),
                            SizedBox(height: 4),
                          ],
                        ),
                      ),
                      SizedBox(height: 12),
                      Row(
                        children: [
                          Container(
                            width: 24,
                            height: 25,
                            decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(8),
                            ),

                            // --DATE--
                            child: Icon(
                              Icons.calendar_month_outlined,
                              color: Colors.red,
                              size: 24,
                            ),
                          ),
                          SizedBox(width: 8),
                          Text(
                            "DATE",
                            style: TextStyle(
                              color: Colors.black,
                              fontWeight: FontWeight.bold,
                              fontSize: 20,
                            ),
                          ),
                        ],
                      ),
                      SizedBox(height: 12),
                      Align(
                        alignment: Alignment.centerLeft,
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            GestureDetector(
                              onTap: _selectDate,
                              child: Row(
                                children: [
                                  Text(
                                    "${selectedDate.day}/${selectedDate.month}/${selectedDate.year}",
                                    style: TextStyle(
                                      color: Colors.black,
                                      fontSize: 15,
                                      fontFamily: "Inter",
                                      fontWeight: FontWeight.w500,
                                    ),
                                  ),
                                  SizedBox(width: 4),
                                  Icon(
                                    Icons.keyboard_arrow_down,
                                    color: Colors.black,
                                    size: 18,
                                  ),
                                ],
                              ),
                            ),
                            SizedBox(height: 10),
                            Container(
                              height: 0.5,
                              color: Colors.black,
                            ),
                          ],
                        ),
                      ),
                      SizedBox(height: 12),
                      ElevatedButton(
                        onPressed: () {
                          context.push('/detail');
                        },
                        child: Text("Search"),
                        style: ElevatedButton.styleFrom(
                          backgroundColor: Colors.red,
                          padding: EdgeInsets.symmetric(
                            vertical: 12,
                            horizontal: 16,
                          ),
                          textStyle: TextStyle(
                            fontSize: 18,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
