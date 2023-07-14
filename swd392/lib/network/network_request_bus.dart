import 'dart:convert';
import 'package:flutter/foundation.dart';
import '../model/bus.dart';
import 'package:http/http.dart' as http;

class NetworkRequestBus {
  static const String url = 'https://nhatrangbustourbackend.azurewebsites.net/api/buses/';

  static List<Bus> parsePostBus(String responseBody){
    var list = json.decode(responseBody) as List<dynamic>;
    List<Bus> bus = list.map((model) => Bus.fromJson(model)).toList();
    return bus;
  }



  static Future<List<Bus>> fetchBus({int page = 1}) async{
    final response = await http.get(Uri.parse(url));
    if (response.statusCode == 200){
      return compute(parsePostBus, response.body);
    } else if(response.statusCode == 404){
      throw Exception('Not Found');
    } else if(response.statusCode == 401){
      throw Exception('Unauthorized bus');
    } else {
      throw Exception('Can not get POST BUS');
    }
  }
}

