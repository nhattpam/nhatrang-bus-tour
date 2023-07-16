import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:swd392/network/network_request_route.dart';
import 'package:swd392/model/route.dart' as postfix;

class BusBookingDetailPage extends StatefulWidget {
  const BusBookingDetailPage({super.key});

  @override
  State<BusBookingDetailPage> createState() => _BusBookingDetailPageState();
}

class _BusBookingDetailPageState extends State<BusBookingDetailPage> {
  late String startRoute;
  late String endRoute;
  List<postfix.Route>  routes = []; // List to hold the fetched stations

  @override
  void initState() {
    super.initState();
    startRoute = '';
    endRoute = '';

    fetchRoutes(); // Fetch the stations from the API
  }

  Future<void> fetchRoutes() async {
    try {
      List<postfix.Route> fetchRoutes = await NetworkRequestRoute.fetchRoute();
      setState(() {
        routes = fetchRoutes; // Corrected the variable name here
        if (routes.isNotEmpty) {
          startRoute = routes[0].routeName ?? '';
          endRoute = routes[0].routeName ?? '';
        }

        // Print the routeName of each route
        print('day la list');
        for (postfix.Route r in routes) {
          if (r.routeName != null && r.routeName!.contains('-')) {
            String locationBeforeDash = r.routeName!.split('-').first.trim();
            String locationAfterDash = r.routeName!.split('-').last.trim();
            print(locationAfterDash);
          } else {
            // Handle the case when the routeName doesn't contain "-"
            print('Invalid routeName format: ${r.routeName}');
          }
        }
      });
    } catch (e) {
      print('Failed to fetch routes: $e');
    }
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        elevation: 1,
        centerTitle: true,
        backgroundColor: Colors.red,
        foregroundColor: Colors.white,
        title: const Text(
          "Nha Trang Bus Search",
          style: TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.bold,
          ),
        ),
        leading: IconButton(
          icon: Icon(Icons.arrow_back_ios),
          onPressed: () {
            Navigator.of(context).pop();
          },
          color: Colors.white,
        ),
      ),
      body: Column(
        children: [
          Container(
            margin: const EdgeInsets.all(16),
            height: 64,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(4),
              border: Border.all(
                color: Colors.grey,
              ),
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                IconButton(
                  onPressed: () {

                  },
                  icon: const Icon(
                    Icons.filter_list_outlined,
                  ),
                ),
                const Text(
                  "Search for Trips",
                  style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                  ),
                )
              ],
            ),
          ),
          const Divider(),
          Expanded(
            child: SingleChildScrollView(
              child: Column(
              children: [
                Container(
                  padding: const EdgeInsets.all(16),
                  child: Column(
                    children: [
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text("Departure", style: TextStyle(
                                fontSize: 16,
                              ),
                              ),
                              Padding(
                                padding: EdgeInsets.symmetric(
                                  vertical: 8,
                                ),
                                child: Text(
                                  "6:30",
                                  style: TextStyle(
                                    fontWeight: FontWeight.bold,
                                    fontSize: 20,
                                  ),
                                ),
                              ),
                              //startRoute
                              Text(
                                routes.isNotEmpty
                                    ? routes.map((route) => route.routeName?.split('-').first.trim() ?? "Unknown").join("\n ")
                                    : "Unknown",
                                style: TextStyle(
                                  fontSize: 16,
                                ),
                              ),


                            ],
                          ),
                          Column(
                            crossAxisAlignment: CrossAxisAlignment.end,
                            children: [
                              Text("Arrival", style: TextStyle(
                                fontSize: 16,
                              ),
                              ),
                              Padding(
                                padding: EdgeInsets.symmetric(
                                  vertical: 8,
                                ),
                                child: Text(
                                  "10:30",
                                  style: TextStyle(
                                    fontWeight: FontWeight.bold,
                                    fontSize: 20,
                                  ),
                                ),
                              ),
                              //endRoute
                              Text(
                                routes.isNotEmpty
                                    ? routes.map((route) => route.routeName?.split('-').last.trim() ?? "Unknown").join("\n ")
                                    : "Unknown",
                                style: TextStyle(
                                  fontSize: 16,
                                ),
                              ),

                            ],
                          )
                        ],
                      ),
                      Padding(
                        padding: EdgeInsets.symmetric(vertical: 24),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Icon(Icons.accessible),
                            Padding(
                              padding: EdgeInsets.all(8.0),
                              child: Icon(Icons.work),
                            ),
                            Icon(Icons.electrical_services),
                          ],
                        ),
                      ),
                      GestureDetector(
                        onTap: (){
                          context.push("/seat");
                        },
                        child: Container(
                          decoration: BoxDecoration(
                              color: Colors.red,
                              borderRadius: BorderRadius.circular(32)
                          ),
                          padding: EdgeInsets.symmetric(vertical: 16),
                          child: Center(
                            child: Text.rich(TextSpan(
                              children: [
                                TextSpan(
                                  text: "FROM",
                                  style: TextStyle(
                                      color: Colors.white,
                                      fontSize: 20
                                  ),
                                ),
                                TextSpan(
                                  text: ' 10\$',
                                  style: TextStyle(
                                      color: Colors.white,
                                      fontWeight: FontWeight.bold,
                                      fontSize: 22
                                  ),
                                ),
                              ],
                            ),
                            ),
                          ),
                        ),
                      )
                    ],
                  ),
                ),
                Divider(),
              ],
            ),
            ),
          ),
        ],
      ),
    );
  }
}
