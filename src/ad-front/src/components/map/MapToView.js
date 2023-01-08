import {GoogleMap, Marker, useJsApiLoader} from "@react-google-maps/api";
import React from "react";

const containerStyle = {
    height: 480,
    width: "97.5%"
};

const MapToView = props => {
    const {isLoaded} = useJsApiLoader({
        id: 'google-map-script',
        googleMapsApiKey: process.env.NEXT_PUBLIC_GOOGLE_MAPS_API_KEY
    })

    const [map, setMap] = React.useState(null)

    const [center, setCenter] = React.useState({
        lat: 52.227716,
        lng: 21.002394
    })

    const onLoad = React.useCallback(function callback(map) {
        const bounds = new window.google.maps.LatLngBounds(center);
        map.fitBounds(bounds);
        setMap(map)
    }, [])

    const onUnmount = React.useCallback(function callback(map) {
        setMap(null)
    }, [])

    return isLoaded ? (
        <GoogleMap
            mapContainerStyle={containerStyle}
            center={{
                lat: props.offer.lat,
                lng: props.offer.lon
            }}
            zoom={5}
            onLoad={onLoad}
            onUnmount={onUnmount}
        >
            <Marker position={{
                lat: props.offer.lat,
                lng: props.offer.lon
            }}
            />
        </GoogleMap>
    ) : <></>
}

export default React.memo(MapToView)