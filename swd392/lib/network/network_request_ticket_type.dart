import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:swd392/model/TicketType.dart';

//change class
class NetworkRequestTicketType {
  static const String url = 'https://nhatrangbustourbackend.azurewebsites.net/api/tickettypes/'; //change link
  static const String bearerToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGF0cmFuZ2J1c0BnbWFpbC5jb20iLCJpYXQiOjE2ODk3NzQ0NjcsImV4cCI6MTY5MDM3OTI2N30.XX3PWi7MgZpy8nKwucCkB9jyC6oQbV-MqV0JaGqFrSOoAzZ_5zraq5-_KeimvI0yHdZbd4M9AsCFsdbmtlM_Uw';

  //change here
  static List<TicketType> parsePostTicketType(String responseBody){
    var list = json.decode(responseBody) as List<dynamic>;
    List<TicketType> ticketType = list.map((model) => TicketType.fromJson(model)).toList();
    return ticketType;
  }

  //change here
  static Future<List<TicketType>> fetchTicketType({int page = 1}) async{
    final response = await http.get(
      Uri.parse(url),
      headers: {'Authorization': 'Bearer $bearerToken'},
    );
    if (response.statusCode == 200){
      return compute(parsePostTicketType, response.body);
    } else if(response.statusCode == 404){
      throw Exception('Not Found');
    } else if(response.statusCode == 401){
      throw Exception('Unauthorized ticketType');
    } else {
      throw Exception('Can not get POST TicketType');
    }
  }
}