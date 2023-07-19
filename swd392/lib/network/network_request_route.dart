import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:swd392/model/route.dart';
import 'package:http/http.dart' as http;

//change class
class NetworkRequestRoute {
  static const String url = 'https://nhatrangbustourbackend.azurewebsites.net/api/routes/'; //change link
  static const String bearerToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGF0cmFuZ2J1c0BnbWFpbC5jb20iLCJpYXQiOjE2ODk3NzQ0NjcsImV4cCI6MTY5MDM3OTI2N30.XX3PWi7MgZpy8nKwucCkB9jyC6oQbV-MqV0JaGqFrSOoAzZ_5zraq5-_KeimvI0yHdZbd4M9AsCFsdbmtlM_Uw';

  //change here
  static List<Route> parsePostRoute(String responseBody){
    var list = json.decode(responseBody) as List<dynamic>;
    List<Route> route = list.map((model) => Route.fromJson(model)).toList();
    return route;
  }

  //change here
  static Future<List<Route>> fetchRoute({int page = 1}) async{
    final response = await http.get(
      Uri.parse(url),
      headers: {'Authorization': 'Bearer $bearerToken'},
    );
    if (response.statusCode == 200){
      return compute(parsePostRoute, response.body);
    } else if(response.statusCode == 404){
      throw Exception('Not Found');
    } else if(response.statusCode == 401){
      throw Exception('Unauthorized route');
    } else {
      throw Exception('Can not get POST ROUTE');
    }
  }
}