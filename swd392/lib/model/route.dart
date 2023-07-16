class Route {
  int? routeId;
  String? routeName;
  int? parentRouteID;
  List<Trip>? trip;
  List<TicketType>? ticketType;
  List<PriceFrame>? priceFrame;

  Route(
      {this.routeId,
        this.routeName,
        this.parentRouteID,
        this.trip,
        this.ticketType,
        this.priceFrame});

  Route.fromJson(Map<String, dynamic> json) {
    routeId = json['routeId'];
    routeName = json['routeName'];
    parentRouteID = json['parentRouteID'];
    if (json['trip'] != null) {
      trip = <Trip>[];
      json['trip'].forEach((v) {
        trip!.add(new Trip.fromJson(v));
      });
    }
    if (json['ticketType'] != null) {
      ticketType = <TicketType>[];
      json['ticketType'].forEach((v) {
        ticketType!.add(new TicketType.fromJson(v));
      });
    }
    if (json['priceFrame'] != null) {
      priceFrame = <PriceFrame>[];
      json['priceFrame'].forEach((v) {
        priceFrame!.add(new PriceFrame.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['routeId'] = this.routeId;
    data['routeName'] = this.routeName;
    data['parentRouteID'] = this.parentRouteID;
    if (this.trip != null) {
      data['trip'] = this.trip!.map((v) => v.toJson()).toList();
    }
    if (this.ticketType != null) {
      data['ticketType'] = this.ticketType!.map((v) => v.toJson()).toList();
    }
    if (this.priceFrame != null) {
      data['priceFrame'] = this.priceFrame!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class Trip {
  int? tripID;
  String? departureTime;
  String? arrivalTime;
  List<Ticket>? ticket;

  Trip({this.tripID, this.departureTime, this.arrivalTime, this.ticket});

  Trip.fromJson(Map<String, dynamic> json) {
    tripID = json['tripID'];
    departureTime = json['departureTime'];
    arrivalTime = json['arrivalTime'];
    if (json['ticket'] != null) {
      ticket = <Ticket>[];
      json['ticket'].forEach((v) {
        ticket!.add(new Ticket.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['tripID'] = this.tripID;
    data['departureTime'] = this.departureTime;
    data['arrivalTime'] = this.arrivalTime;
    if (this.ticket != null) {
      data['ticket'] = this.ticket!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class Ticket {
  int? ticketId;
  String? passengerName;
  String? passengerPhone;
  String? passengerEmail;
  String? feedback;

  Ticket(
      {this.ticketId,
        this.passengerName,
        this.passengerPhone,
        this.passengerEmail,
        this.feedback});

  Ticket.fromJson(Map<String, dynamic> json) {
    ticketId = json['ticketId'];
    passengerName = json['passengerName'];
    passengerPhone = json['passengerPhone'];
    passengerEmail = json['passengerEmail'];
    feedback = json['feedback'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['ticketId'] = this.ticketId;
    data['passengerName'] = this.passengerName;
    data['passengerPhone'] = this.passengerPhone;
    data['passengerEmail'] = this.passengerEmail;
    data['feedback'] = this.feedback;
    return data;
  }
}

class TicketType {
  int? ticketTypeId;
  String? ticketTypeName;
  List<PriceFrameTicket>? priceFrameTicket;
  List<Ticket>? ticket;

  TicketType(
      {this.ticketTypeId,
        this.ticketTypeName,
        this.priceFrameTicket,
        this.ticket});

  TicketType.fromJson(Map<String, dynamic> json) {
    ticketTypeId = json['ticketTypeId'];
    ticketTypeName = json['ticketTypeName'];
    if (json['priceFrameTicket'] != null) {
      priceFrameTicket = <PriceFrameTicket>[];
      json['priceFrameTicket'].forEach((v) {
        priceFrameTicket!.add(new PriceFrameTicket.fromJson(v));
      });
    }
    if (json['ticket'] != null) {
      ticket = <Ticket>[];
      json['ticket'].forEach((v) {
        ticket!.add(new Ticket.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['ticketTypeId'] = this.ticketTypeId;
    data['ticketTypeName'] = this.ticketTypeName;
    if (this.priceFrameTicket != null) {
      data['priceFrameTicket'] =
          this.priceFrameTicket!.map((v) => v.toJson()).toList();
    }
    if (this.ticket != null) {
      data['ticket'] = this.ticket!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class PriceFrameTicket {
  int? priceFrameTicketId;
  int? price;

  PriceFrameTicket({this.priceFrameTicketId, this.price});

  PriceFrameTicket.fromJson(Map<String, dynamic> json) {
    priceFrameTicketId = json['priceFrameTicketId'];
    price = (json['price'] as double?)?.toInt(); // Convert double to int
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['priceFrameTicketId'] = this.priceFrameTicketId;
    data['price'] = this.price;
    return data;
  }
}

class PriceFrame {
  int? priceFrameId;
  String? priceFrameName;
  List<Trip>? trip;
  List<PriceFrameTicket>? priceFrameTicket;

  PriceFrame(
      {this.priceFrameId,
        this.priceFrameName,
        this.trip,
        this.priceFrameTicket});

  PriceFrame.fromJson(Map<String, dynamic> json) {
    priceFrameId = json['priceFrameId'];
    priceFrameName = json['priceFrameName'];
    if (json['trip'] != null) {
      trip = <Trip>[];
      json['trip'].forEach((v) {
        trip!.add(new Trip.fromJson(v));
      });
    }
    if (json['priceFrameTicket'] != null) {
      priceFrameTicket = <PriceFrameTicket>[];
      json['priceFrameTicket'].forEach((v) {
        priceFrameTicket!.add(new PriceFrameTicket.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['priceFrameId'] = this.priceFrameId;
    data['priceFrameName'] = this.priceFrameName;
    if (this.trip != null) {
      data['trip'] = this.trip!.map((v) => v.toJson()).toList();
    }
    if (this.priceFrameTicket != null) {
      data['priceFrameTicket'] =
          this.priceFrameTicket!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}
