package net.william278.huskhomes.util;

import net.william278.huskhomes.HuskHomes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class BulkAction {

    private final HuskHomes plugin;
    private final Action action;
    private Map<Filter, String> filters;
    @Nullable
    private final String targetValue;

    private BulkAction(@NotNull HuskHomes plugin, @NotNull Action action, @NotNull Map<Filter, String> filters,
                       @Nullable String targetValue) {
        this.plugin = plugin;
        this.action = action;
        this.filters = filters;
        this.targetValue = targetValue;
    }

    @NotNull
    public static Builder builder(@NotNull HuskHomes plugin, @NotNull Action action) {
        return new Builder(plugin, action);
    }

    /**
     * What type of teleportation object to operate on
     */
    public enum Type {
        HOME,
        WARP,
    }

    /**
     * Different types of bulk actions that can be performed
     */
    public enum Action {
        DELETE,
        MOVE_WORLD,
        MOVE_SERVER
    }

    /**
     * Different source filters
     */
    public enum Filter {
        USER,
        WORLD,
        SERVER
    }

    /**
     * A builder for carrying out {@link BulkAction}s
     */
    public static class Builder {
        private final HuskHomes plugin;
        private final Action action;
        private final Map<Filter, String> sourceValues = new HashMap<>();
        private String targetValue;

        protected Builder(@NotNull HuskHomes plugin, @NotNull Action action) {
            this.plugin = plugin;
            this.action = action;
        }

        @NotNull
        public Builder sourceWorld(@NotNull String sourceWorld) {
            this.sourceValues.put(Filter.WORLD, sourceWorld);
            return this;
        }

        @NotNull
        public Builder sourceServer(@NotNull String sourceServer) {
            this.sourceValues.put(Filter.SERVER, sourceServer);
            return this;
        }

        @NotNull
        public Builder sourceUser(@NotNull String sourceUser) {
            this.sourceValues.put(Filter.USER, sourceUser);
            return this;
        }

        @NotNull
        public Builder targetValue(String targetValue) {
            this.targetValue = targetValue;
            return this;
        }

        @NotNull
        public BulkAction build() {
            return new BulkAction(plugin, action, sourceValues, targetValue);
        }
    }

}
