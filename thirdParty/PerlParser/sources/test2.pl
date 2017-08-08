use 5.010;
use strict;
use warnings;

# See http://wiki.cpantesters.org/wiki/CPANAuthorNotes

##{ $perl_prereq ? qq[use $perl_prereq;] : ''; ##}

if ($^O eq 'MSWin32') {
    warn "I do not support Windows yet. Sorry.\n";
    exit 0;
}

sub git {
    my $args = @_;
    my $output = `git $args`;
    if ($? == -1) {
        warn "Failed to execute 'git $args': $!\n";
        exit 0;
    } elsif ($? != 0) {
        warn "Command 'git $args' died with exit code=$?\n";
        exit 0;
    }
    return $output;
}

# Check if we have a proper git
chomp(my $git_version = git('version'));