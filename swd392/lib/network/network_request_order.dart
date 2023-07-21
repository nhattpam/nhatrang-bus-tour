import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:swd392/model/order.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

//change class
class NetworkRequestSaveOrder {
  static const String url = 'https://nhatrangbustourbackend.azurewebsites.net/api/orders/'; //change link
  static const String bearerToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGF0cmFuZ2J1c0BnbWFpbC5jb20iLCJpYXQiOjE2ODk3NzQ0NjcsImV4cCI6MTY5MDM3OTI2N30.XX3PWi7MgZpy8nKwucCkB9jyC6oQbV-MqV0JaGqFrSOoAzZ_5zraq5-_KeimvI0yHdZbd4M9AsCFsdbmtlM_Uw';

  //change here
  static List<Order> parsePostOrder(String responseBody){
    var list = json.decode(responseBody) as List<dynamic>;
    List<Order> order = list.map((model) => Order.fromJson(model)).toList();
    return order;
  }
  //get userEmail from SharePreferences
  static Future<String?> getUserEmailFromSession() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getString('userEmail');
  }


  //change here


  static Future<int> fetchUserId({int page = 1}) async{
    int userId = 0;
    String? userEmail = await getUserEmailFromSession();
    print("day la email 99999: $userEmail"); // Print the userEmail

    String baseUrl = '$url$userEmail';

    print( "DAY LA LINK CUA TAO: $baseUrl");

    final response = await http.get(
      Uri.parse(baseUrl),
      headers: {'Authorization': 'Bearer $bearerToken'},
    );
    if (response.statusCode == 200) {
      // Successful response, parse the response body and extract the userId
      try {
        userId = int.parse(response.body);
      } catch (e) {
        throw Exception('Error parsing userId');
      }
      return userId;
    } else if (response.statusCode == 404) {
      throw Exception('Not Found');
    } else if (response.statusCode == 401) {
      throw Exception('Unauthorized user ID');
    } else {
      throw Exception('Can not get POST ID');
    }
  }
}