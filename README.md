to do “Book a Trip” с един бутон

Създай страница /demo/book-trip с форма:

Избираш потребител

Въвеждаш дестинация и максимална цена

При submit:

Контролерът извиква BookingFacade.findBestFlights() и findBestHotels()

Избира първия подходящ flight и hotel

Извиква BookingFacade.bookTrip()

Резултатът: една резервация е създадена само чрез фасадата, без контролера да се занимава с FlightService и HotelService.
