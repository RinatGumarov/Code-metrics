sub max {
    my $max = shift(@_);
    foreach $foo (@_) {
        $max = $foo if $max < $foo;
    }
    return $max;
}
$mx = max 3, 2, 1 + max 3, 2, 1;