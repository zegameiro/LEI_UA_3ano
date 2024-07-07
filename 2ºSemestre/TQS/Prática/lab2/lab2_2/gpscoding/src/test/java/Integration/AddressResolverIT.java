package Integration;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tqs.connection.ISimpleHttpClient;
import tqs.connection.TqsBasicHttpClient;
import tqs.geoCoding.Address;
import tqs.geoCoding.AddressResolverService;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class AddressResolverIT {

    private ISimpleHttpClient httpClient;
    private AddressResolverService resolverService;


    @BeforeEach
    public void init(){
        httpClient = new TqsBasicHttpClient();
        resolverService = new AddressResolverService(httpClient);
    }

    @Test
    void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {
        Address result = resolverService.findAddressForLocation(40.634159, -8.65795).get();

        Address expected = new Address("Avenida João Jacinto de Magalhães", "Aveiro", "3810-149", "");

        assertEquals(expected.getRoad(), result.getRoad());
    }

    @Test
    void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {
        Optional<Address> address = resolverService.findAddressForLocation(-361, -361);
        assertThrows(NoSuchElementException.class, () -> address.get().getRoad());
    }

}

