package de.fynder.m4b_tagger.utils;


import org.joda.time.DateTime;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AudioBook {
    public String name; // title
    public String sortName;
    public String artist;
    public String sortArtist;
    public String composer;
    public String sortComposer;
    public String album;
    public String sortAlbum;
    public String albumArtist;
    public String sortAlbumArtist;

    public String genre;
    public String grouping;
    public String comments;

    public String encodingTool;
    public String encodedBy;
    public String copyright;

    public String lyrics;
    public String description;

    public DateTime releaseDate;

    // https://github.com/pcwalton/mp4v2/blob/e8d9272cf98bb573264a72c4c462c841f8900857/src/itmf/type.cpp
    // public ? genreType
    public int tempo; // uint
    public int artworkCount; // unit, cover art pieces

    public boolean compilation; // yes, no
    public boolean gapless; // yes, no

    // public ? contentRating
    public boolean hdVideo; // yes, no

    // public ? mediaType

    public String tvShow;
    public String softTvShow;
    // public ? tvNetwork

    BufferedImage cover;


    public IndexTotalItem track = new IndexTotalItem();
    public IndexTotalItem disk = new IndexTotalItem();
    public List<Chapter> chapters = new ArrayList();

}

/*
// https://github.com/pcwalton/mp4v2/blob/master/util/mp4info.cpp
 if ( tags->tvEpisodeID ) {
 fprintf( stdout, " TV Episode Number: %s\n", tags->tvEpisodeID );
 }
 if ( tags->description ) {
 fprintf( stdout, " Short Description: %s\n", tags->description );
 }
 if ( tags->longDescription ) {
 fprintf( stdout, " Long Description: %s\n", tags->longDescription );
 }
 if ( tags->lyrics ) {
 fprintf( stdout, " Lyrics: \n %s\n", tags->lyrics );
 }
 if ( tags->tvEpisode ) {
 fprintf( stdout, " TV Episode: %u\n", *tags->tvEpisode );
 }
 if ( tags->tvSeason ) {
 fprintf( stdout, " TV Season: %u\n", *tags->tvSeason );
 }
 if ( tags->podcast) {
 fprintf( stdout, " Podcast: %s\n", *tags->podcast ? "yes" : "no" );
 }
 if ( tags->keywords ) {
 fprintf( stdout, " Keywords: %s\n", tags->keywords );
 }
 if ( tags->category ) {
 fprintf( stdout, " Category: %s\n", tags->category );
 }
 if ( tags->contentID ) {
 fprintf( stdout, " Content ID: %u\n", *tags->contentID );
 }
 if ( tags->artistID ) {
 fprintf( stdout, " Artist ID: %u\n", *tags->artistID );
 }
 if ( tags->playlistID ) {
 fprintf( stdout, " Playlist ID: %llu\n", *tags->playlistID );
 }
 if ( tags->genreID ) {
 fprintf( stdout, " Genre ID: %u\n", *tags->genreID );
 }
 if ( tags->composerID ) {
 fprintf( stdout, " Composer ID: %u\n", *tags->composerID );
 }
 if ( tags->xid ) {
 fprintf( stdout, " xid: %s\n", tags->xid );
 }
 if ( tags->iTunesAccount ) {
 fprintf( stdout, " iTunes Account: %s\n", tags->iTunesAccount );
 }
 if ( tags->iTunesAccountType ) {
 string s = itmf::enumAccountType.toString( static_cast<itmf::AccountType>( *tags->iTunesAccountType ), true );
 fprintf( stdout, " iTunes Account Type: %s\n", s.c_str() );
 }
 if ( tags->purchaseDate ) {
 fprintf( stdout, " Purchase Date: %s\n", tags->purchaseDate );
 }
 if ( tags->iTunesCountry ) {
 string s = itmf::enumCountryCode.toString( static_cast<itmf::CountryCode>( *tags->iTunesCountry ), true );
 fprintf( stdout, " iTunes Store Country: %s\n", s.c_str() );
 }
 */
