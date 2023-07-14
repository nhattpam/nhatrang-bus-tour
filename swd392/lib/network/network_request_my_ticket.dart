import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:swd392/model/ticket.dart';
import 'package:http/http.dart' as http;

//change class
class NetworkRequestTicket {
  static const String url = 'https://nhatrangbustourbackend.azurewebsites.net/api/tickets/'; //change link
  static const String bearerToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGF0cmFuZ2J1c0BnbWFpbC5jb20iLCJpYXQiOjE2ODkwMDI3NzEsImV4cCI6MTY4OTYwNzU3MX0.EF-rdCb0QK0bKZoWhjdhqYMlhPamT4HIyLHNnNhT3FzJvSzHiFgfIlJjZiHMKxnkyt1WQIFAgcPIoSpGUT6OKA';

  //change here
  static List<Ticket> parsePostTicket(String responseBody){
    var list = json.decode(responseBody) as List<dynamic>;
    List<Ticket> ticket = list.map((model) => Ticket.fromJson(model)).toList();
    return ticket;
  }

  //change here
  static Future<List<Ticket>> fetchTicket({int page = 1}) async{
    final response = await http.get(
      Uri.parse(url),
      headers: {'Authorization': 'Bearer $bearerToken'},
    );
    if (response.statusCode == 200){
      return compute(parsePostTicket, response.body);
    } else if(response.statusCode == 404){
      throw Exception('Not Found');
    } else if(response.statusCode == 401){
      throw Exception('Unauthorized ticket');
    } else {
      throw Exception('Can not get POST TICKET');
    }
  }
}